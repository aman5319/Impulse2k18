package com.amidezcod.impulse2k18.activity

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v4.content.FileProvider
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.amidezcod.impulse2k18.BitmapUtils
import com.amidezcod.impulse2k18.adapter.ImpulseWallViewHolder
import com.amidezcod.impulse2k18.modal.ImpulseWallModel
import com.bumptech.glide.Glide
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import id.zelory.compressor.Compressor
import impulse2k18.R
import kotlinx.android.synthetic.main.activity_impulse_wall.*
import java.io.File
import java.io.FileInputStream
import java.util.*


class ImpulseWallActivity : AppCompatActivity() {

    lateinit var firebaseRecyclerAdapter: FirebaseRecyclerAdapter<ImpulseWallModel, ImpulseWallViewHolder>
    lateinit var databaseReference: DatabaseReference
    private lateinit var firebaseStorage: FirebaseStorage
    private lateinit var mTempPhotoPath: String
    private lateinit var storage: FirebaseStorage
    private lateinit var storageReference: StorageReference
    private val RC_PHOTO_PICKER_REQUEST: Int = 123
    private val REQUEST_STORAGE_PERMISSION: Int = 321
    private val REQUEST_IMAGE_CAPTURE: Int = 213
    private val RC_SIGN_IN = 345
    private var downloadedUrl: Uri? = null
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_impulse_wall)
        setupToolbar()
        disableEnableFab()
        setupFirebase()
        setupRecyclerView()
        setupRecyclerViewAdatpter()
        setupPhotoPicker()
        setUpCamera()
        sendToFirebase()
        if (firebaseAuth.currentUser != null)
            Glide.with(this).load(firebaseAuth.currentUser!!.photoUrl).into(user_profile_pic)

    }


    private fun disableEnableFab() {
        user_message_send_fab.isEnabled = false
        user_message_send_fab.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this@ImpulseWallActivity, android.R.color.holo_red_dark))
        user_editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (downloadedUrl == null) {
                    user_message_send_fab.isEnabled = s.toString().trim().isNotEmpty()
                    if (s.toString().trim().isNotEmpty())
                        user_message_send_fab.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this@ImpulseWallActivity, R.color.colorAccent))
                    else
                        user_message_send_fab.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this@ImpulseWallActivity, android.R.color.holo_red_dark))
                } else {
                    if (attachment.visibility == View.VISIBLE) {
                        user_message_send_fab.isEnabled = true
                        user_message_send_fab.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this@ImpulseWallActivity, R.color.colorAccent))
                    }
                }
            }

        })
    }


    private fun sendToFirebase() {
        user_message_send_fab.setOnClickListener({
            if (firebaseAuth.currentUser != null) {
                val profilePic: Uri? = firebaseAuth.currentUser!!.photoUrl
                val name = firebaseAuth.currentUser!!.displayName!!
                if (user_editText.text.toString().isNotEmpty() && downloadedUrl == null) {
                    val a = ImpulseWallModel(profilePic.toString(), name, "", user_editText.text.toString().trim())
                    databaseReference.push().setValue(a)
                    user_message_send_fab.isEnabled = false
                    user_message_send_fab.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this@ImpulseWallActivity, android.R.color.holo_red_dark))

                } else if (downloadedUrl != null && downloadedUrl.toString().isNotEmpty() && user_editText.text.toString().trim().isEmpty()) {
                    val a = ImpulseWallModel(profilePic.toString(), name, downloadedUrl.toString(), "")
                    databaseReference.push().setValue(a)
                    downloadedUrl = null
                    attachment.visibility = View.GONE
                    user_message_send_fab.isEnabled = false
                    user_message_send_fab.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this@ImpulseWallActivity, android.R.color.holo_red_dark))

                } else if (downloadedUrl != null && downloadedUrl.toString().isNotEmpty() && user_editText.text.isNotEmpty()) {
                    val a = ImpulseWallModel(profilePic.toString(), name, downloadedUrl.toString(), user_editText.text.toString().trim())
                    databaseReference.push().setValue(a)
                    downloadedUrl = null
                    attachment.visibility = View.GONE
                    user_message_send_fab.isEnabled = false
                    user_message_send_fab.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this@ImpulseWallActivity, android.R.color.holo_red_dark))
                } else {
                    Toast.makeText(this@ImpulseWallActivity, "nope", Toast.LENGTH_SHORT).show()
                }
                recyclerView_impulse_wall.scrollToPosition(recyclerView_impulse_wall.adapter.itemCount - 1)
                user_editText.setText("")
                val inputMethodManager: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(this@ImpulseWallActivity.currentFocus.windowToken, 0)
            } else {
                startActivityForResult(
                        AuthUI.getInstance()
                                .createSignInIntentBuilder()
                                .setAvailableProviders(Arrays.asList(
                                        AuthUI.IdpConfig.EmailBuilder().build(),
                                        AuthUI.IdpConfig.GoogleBuilder().build()))
                                .build(),
                        RC_SIGN_IN)
            }
        })
    }

    private fun setUpCamera() {
        camera.setOnClickListener {
            // Check for the external storage permission
            if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {

                // If you do not have permission, request it
                ActivityCompat.requestPermissions(this,
                        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                        REQUEST_STORAGE_PERMISSION)
            } else {
                // Launch the camera if the permission exists
                launchCamera()
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,
                                            grantResults: IntArray) {
        // Called when you request permission to read and write to external storage
        when (requestCode) {
            REQUEST_STORAGE_PERMISSION -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // If you get permission, launch the camera
                    launchCamera()
                } else {
                    // If you do not get permission, show a Toast
                    Toast.makeText(this, "permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    private fun launchCamera() {

        // Create the capture image intent
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(packageManager) != null) {
            // Create the temporary File where the photo should go
            var photoFile: File? = null
            try {
                photoFile = BitmapUtils.createTempImageFile(this)
            } catch (ex: Exception) {
                // Error occurred while creating the File
                ex.printStackTrace()
            }

            // Continue only if the File was successfully created
            if (photoFile != null) {

                // Get the path of the temporary file
                mTempPhotoPath = photoFile.absolutePath

                // Get the content URI for the image file
                val photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile)

                // Add the URI so the camera can store the image
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)

                // Launch the camera activity
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {

            val resampleBitmap = Compressor(this).compressToBitmap(File(mTempPhotoPath))
            val savedImagePath: String = BitmapUtils.saveImage(this@ImpulseWallActivity, resampleBitmap)
            uploadTaskForCamera(savedImagePath)

        } else if (requestCode == RC_PHOTO_PICKER_REQUEST && resultCode == Activity.RESULT_OK) {

            val imageUri = data?.data

            val bitmapPath = BitmapUtils.saveImage(this, BitmapFactory.decodeStream(contentResolver.openInputStream(imageUri)))

            val resampleBitmap: Bitmap = Compressor(this).compressToBitmap(File(bitmapPath))


            File(bitmapPath).run {
                if (exists()) {
                    delete()
                }
            }
            val savedImagePath: String = BitmapUtils.saveImage(this@ImpulseWallActivity, resampleBitmap)
            uploadTaskForCamera(savedImagePath)
        } else if (requestCode == RC_SIGN_IN && resultCode == RESULT_OK) {
            Glide.with(this).load(firebaseAuth.currentUser!!.photoUrl).into(user_profile_pic)
            Toast.makeText(this, "Signed in!", Toast.LENGTH_SHORT).show()

        } else {
        }
    }


    private fun uploadTaskForCamera(savedImagePath: String) {
        val file = File(savedImagePath)
        val stream = FileInputStream(file)
        Toast.makeText(this@ImpulseWallActivity, "upload in progress", Toast.LENGTH_SHORT).show()
        storageReference.child(Uri.fromFile(file).lastPathSegment).putStream(stream)
                .addOnSuccessListener(this) { p0 ->
                    downloadedUrl = p0?.downloadUrl
                    Toast.makeText(this@ImpulseWallActivity, "Image upload success", Toast.LENGTH_SHORT).show()
                    attachment.visibility = View.VISIBLE
                    user_message_send_fab.isEnabled = true
                    user_message_send_fab.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this@ImpulseWallActivity, R.color.colorAccent))

                }
                .addOnFailureListener(this) { p0 ->
                    p0.printStackTrace()
                    user_message_send_fab.isEnabled = true
                    user_message_send_fab.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this@ImpulseWallActivity, R.color.colorAccent))

                }
    }


    private fun setupPhotoPicker() {
        image_picker.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true)
            startActivityForResult(Intent.createChooser(intent, "Choose a photo"), RC_PHOTO_PICKER_REQUEST)
        }
    }

    private fun setupFirebase() {
        databaseReference = FirebaseDatabase.getInstance().reference.child("impulseWall")
        storage = FirebaseStorage.getInstance()
        storageReference = storage.reference
        firebaseAuth = FirebaseAuth.getInstance()
    }

    private fun setupRecyclerViewAdatpter() {
        val firebaseRecyclerOptions: FirebaseRecyclerOptions<ImpulseWallModel> =
                FirebaseRecyclerOptions
                        .Builder<ImpulseWallModel>()
                        .setQuery(databaseReference
                                .limitToLast(50), ImpulseWallModel::class.java)
                        .build()
        firebaseRecyclerAdapter =
                object : FirebaseRecyclerAdapter<ImpulseWallModel, ImpulseWallViewHolder>(firebaseRecyclerOptions) {
                    override fun onDataChanged() {
                        super.onDataChanged()
                    }

                    override fun onError(error: DatabaseError) {
                        super.onError(error)
                    }

                    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ImpulseWallViewHolder {
                        return ImpulseWallViewHolder(LayoutInflater.from(this@ImpulseWallActivity).inflate(R.layout.impulse_wall_item, parent, false))
                    }

                    override fun onBindViewHolder(holder: ImpulseWallViewHolder, position: Int, model: ImpulseWallModel) {
                        holder.bind(model)
                    }

                }
        recyclerView_impulse_wall.adapter = firebaseRecyclerAdapter
    }

    override fun onStart() {
        super.onStart()
        firebaseRecyclerAdapter.startListening()
    }

    override fun onDestroy() {
        super.onDestroy()
        firebaseRecyclerAdapter.stopListening()
    }

    private fun setupRecyclerView() {
        recyclerView_impulse_wall.setHasFixedSize(true)
        recyclerView_impulse_wall.layoutManager = LinearLayoutManager(this@ImpulseWallActivity
                , LinearLayoutManager.VERTICAL, false)
        val dividerItemDecoration = DividerItemDecoration(this,
                LinearLayoutManager.VERTICAL)
        recyclerView_impulse_wall.addItemDecoration(dividerItemDecoration)
        recyclerView_impulse_wall.itemAnimator = DefaultItemAnimator()
    }

    private fun setupToolbar() {
        setSupportActionBar(impulse_wall_toolbar)
        supportActionBar?.run {
            setDisplayHomeAsUpEnabled(false)
            setDisplayShowTitleEnabled(false)
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        downloadedUrl = null
    }
}
