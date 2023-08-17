package fastcampus.part1.chapter8

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import fastcampus.part1.chapter8.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val imageLoadLauncher =
        registerForActivityResult(ActivityResultContracts.GetMultipleContents()) { uriList ->
            updateImages(uriList)
        }
    private lateinit var binding: ActivityMainBinding
    private lateinit var imageAdapter: ImageAdapter

    private companion object{
        const val REQUEST_READ_EXTERNAL_STORAGE = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loadImageButton.setOnClickListener {
            checkPermission()
        }

        initRecyclerView()
    }

    private fun initRecyclerView(){
        imageAdapter = ImageAdapter(object : ImageAdapter.ItemClickListener {
            override fun onLoadMoreClick() {
                checkPermission()
            }
        })

        binding.imageRecyclerView.apply {
            adapter = imageAdapter //adapter 선언
            layoutManager = GridLayoutManager(context, 2) // 가로로 몇개를 넣을지
        }
    }

    private fun checkPermission() {
        when {
            ContextCompat.checkSelfPermission( // 권한이 있을때
                this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED -> { // 권한이 부여 되었는지 확인
                loadImage() // 사진 이미지 불러오기
            }
            shouldShowRequestPermissionRationale( // 권한이 없을때
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            ) -> {
                showPermissionInfoDialog() //권한 요청 보여주기
            }
            else -> {
                requestReadExternalStorage()
            }
        }

    }

    private fun showPermissionInfoDialog() {
        AlertDialog.Builder(this).apply {
            setMessage("")
            setNegativeButton("취소", null)
            setPositiveButton("동의"){ _, _ ->
                requestReadExternalStorage()
            }
        }.show()
    }

    private fun loadImage() {
        imageLoadLauncher.launch("image/*") // 어떤 타입의 컨텐츠를 가져올 지 선언 할 수 있음
    }

    private fun requestReadExternalStorage() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
            REQUEST_READ_EXTERNAL_STORAGE
        )
    }

    override fun onRequestPermissionsResult( // RequestPermission 확인 하는 것
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            REQUEST_READ_EXTERNAL_STORAGE -> {
                val resultCode = grantResults.firstOrNull() ?: PackageManager.PERMISSION_GRANTED // NPE 방지를 위하여
                if (resultCode == PackageManager.PERMISSION_GRANTED) {
                    loadImage()
                }
            }
        }
    }

    private fun updateImages(uriList: List<Uri>) {
        val images = uriList.map { ImageItems.Image(it) }
        val updatedImages = imageAdapter.currentList.toMutableList().apply { addAll(images) } //기존에 있던 것에서 추가를 하여 보여주기 위해
        imageAdapter.submitList(updatedImages) // notify 관련된 부분을 자동으로 처리해 주는 부분
    }
}