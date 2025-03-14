package com.example.munjangzip.feature.createMemo

import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.munjangzip.R
import com.example.munjangzip.appbar.TopBarWidget
import com.example.munjangzip.ui.BackGroundBubble
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateMemoPic(navController: NavController, bookId: Int) {
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var uploadedImageUrl by remember { mutableStateOf<String?>(null) }
    val viewModel: CreateMemoPicViewModel = hiltViewModel()

    val takePictureLauncher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) { success ->
        if (!success) {
            imageUri = null
        }
    }

    fun saveImageToStorage(context: Context, uri: Uri): File? {
        val inputStream = context.contentResolver.openInputStream(uri) ?: return null
        val fileName = "IMG_${System.currentTimeMillis()}.jpg"
        val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val file = File(storageDir, fileName)

        val outputStream = FileOutputStream(file)
        inputStream.copyTo(outputStream)
        inputStream.close()
        outputStream.close()

        Log.d("CreateMemoPic", "저장된 이미지 경로: ${file.absolutePath}")
        return file
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBarWidget(navController = navController) }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            BackGroundBubble(R.drawable.bubble4)
            Column(
                modifier = Modifier.fillMaxSize().padding(it),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.padding(50.dp))

                // 사진 찍기 버튼
                Button(
                    onClick = {
                        val context = navController.context
                        val uri = viewModel.createImageUri(context)
                        uri?.let {
                            imageUri = it
                            takePictureLauncher.launch(it)
                        }
                    },
                    modifier = Modifier.width(200.dp).height(60.dp).shadow(8.dp, RoundedCornerShape(30.dp)),
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFF2D3))
                ) {
                    Text("사진 찍기", fontSize = 20.sp, color = Color.Gray)
                }

                Spacer(modifier = Modifier.padding(20.dp))
                imageUri?.let { uri ->
                    Button(
                        onClick = {
                            val context = navController.context
                            val file = saveImageToStorage(context, uri)
                            file?.let {
                                viewModel.uploadImage(bookId, it) { imageUrl ->
                                    uploadedImageUrl = imageUrl
                                }
                            }
                        },
                        modifier = Modifier.width(200.dp).height(60.dp).shadow(8.dp, RoundedCornerShape(30.dp)),
                        shape = RoundedCornerShape(20.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE5EFFD))
                    ) {
                        Text("사진 업로드", fontSize = 20.sp, color = Color.Gray)
                    }
                }
            }
        }
    }
}

@HiltViewModel
class CreateMemoPicViewModel @Inject constructor(
    private val apiService: MemoImageApiService
) : ViewModel() {

    fun createImageUri(context: Context): Uri? {
        val contentResolver = context.contentResolver
        val fileName = "IMG_${System.currentTimeMillis()}.jpg"

        // 기존 같은 파일명 존재하면 삭제 (오류떠서..)
        val existingUri = contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            arrayOf(MediaStore.Images.Media._ID),
            "${MediaStore.Images.Media.DISPLAY_NAME} = ?",
            arrayOf(fileName),
            null
        )?.use { cursor ->
            if (cursor.moveToFirst()) {
                val id = cursor.getLong(0)
                Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id.toString())
            } else {
                null
            }
        }

        existingUri?.let { contentResolver.delete(it, null, null) }

        val contentValues = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, fileName)
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
            put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
        }

        return contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
    }

    fun uploadImage(bookId: Int, file: File, onResult: (String?) -> Unit) {
        val requestFile = RequestBody.create("image/jpeg".toMediaTypeOrNull(), file)
        val body = MultipartBody.Part.createFormData("image", file.name, requestFile)

        apiService.uploadMemoImage(bookId, image = body).enqueue(object : Callback<MemoImageResponse> {
            override fun onResponse(call: Call<MemoImageResponse>, response: Response<MemoImageResponse>) {
                if (response.isSuccessful) {
                    onResult(response.body()?.imageUrl)
                    Log.d("CreateMemoPic", "업로드 성공: ${response.body()?.imageUrl}")
                } else {
                    Log.e("CreateMemoPic", "업로드 실패: ${response.errorBody()?.string()}")
                    onResult(null)
                }
            }

            override fun onFailure(call: Call<MemoImageResponse>, t: Throwable) {
                Log.e("CreateMemoPic", "업로드 실패: ${t.message}")
                onResult(null)
            }
        })
    }
}
