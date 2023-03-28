//package com.project.ShareEasy.ui.SetupAccountFragment;
package com.shareeasy.shareeasy.ui.profile;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.auth.UserProfileChangeRequest;
//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.StorageReference;
//import com.google.firebase.storage.UploadTask;
//import com.project.ShareEasy.R;
import com.shareeasy.shareeasy.R;
import com.shareeasy.shareeasy.utils.UIHelpers;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import de.hdodenhof.circleimageview.CircleImageView;

public class SetupAccountFragment extends Fragment {

    private static final String TAG = "SetupAccountFragment";

    private com.project.ShareEasy.ui.fragments.profile.SetupAccountViewModel mViewModel;

    private CircleImageView avatarCIV;
    private TextInputEditText displayNameTXT;
    private MaterialButton updateAccountButton;
    private ProgressBar updateProgressBar;


    TextView ress;
    ProgressDialog progressDialog ;
    String displayName;
    boolean check = true;

    private final int IMAGE_CAPTURE_RC = 22;
    public SharedPreferences sp;

    String EmailName = "email";
    String emailToServer = "";
    String DisplayName = "display_name" ;

    String ImagePathFieldOnServer = "image_url";
    String ImageUploadPathOnSever = "https://shareeasyapp.000webhostapp.com/img_upload_to_server.php";

    String dispNameOriginal,photoUrl;

    private boolean avatarHasChanged = false;
    Bitmap bitmap;

    public static SetupAccountFragment newInstance() {
        return new SetupAccountFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.setup_account_fragment, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sp = getContext().getSharedPreferences("display_name", Context.MODE_PRIVATE);
        sp = getContext().getSharedPreferences("image_url", Context.MODE_PRIVATE);
        sp = this.getActivity().getSharedPreferences("email", Context.MODE_PRIVATE);
        String name = sp.getString("display_name", "");
        photoUrl = sp.getString("image_url", "");
        emailToServer = sp.getString("email", "");


        //Register Views
        avatarCIV = view.findViewById(R.id.avatar_civ);
        displayNameTXT = view.findViewById(R.id.display_name_txt);
        updateAccountButton = view.findViewById(R.id.update_account_button);
        updateProgressBar = view.findViewById(R.id.update_progress_bar);
        ress = view.findViewById(R.id.res);


        displayNameTXT.setText(name);

        Button change_image = view.findViewById(R.id.change_image);

        change_image.setOnClickListener(v -> {
            takePicture();
        });
        avatarCIV.setOnClickListener(v -> {
            takePicture();
        });

        //Populate the previous display name firs
        //displayNameTXT.setText(dispNameOriginal);


        //@todo Display the previous image using glide if it exists
//        String photoUrl = UserInfo.getPhotoUrl();
        Glide.with(this)
                .load(photoUrl)
                .centerCrop()
                .placeholder(R.drawable.ic_account_circle_white)
                .into(avatarCIV);


        //Setup the button click listener to update the account details where necessary
        updateAccountButton.setOnClickListener(v -> {
            updateAccountDetails();
        });

    }


    /**
     * Gets user input from the views, both image and the display name ready for uploading
     */
    private void updateAccountDetails() {

//        Toast.makeText(getContext(), "mail:"+emailToServer, Toast.LENGTH_SHORT).show();

        displayName = displayNameTXT.getText().toString().trim();

        if (TextUtils.isEmpty(displayNameTXT.getText())) {
            displayNameTXT.setError("Display Name is Required");
            displayNameTXT.requestFocus();
            return;
        }

        if (avatarHasChanged) {
            //Get byte array fo=ro bitmap
//            ByteArrayOutputStream baoss = new ByteArrayOutputStream();
//            Bitmap bitmapp = null;
//            bitmapp.compress(Bitmap.CompressFormat.JPEG, 100, baoss);
//            byte[] avatarArrayBytes = baoss.toByteArray();



            ImageUploadToServerFunction();



        } else {

//            emailToServer = sp.getString("email", "");
//            Toast.makeText(getContext(), "mail:"+Email_mail, Toast.LENGTH_SHORT).show();


            if (displayName.equals(dispNameOriginal)){
                UIHelpers.status_dialog(getContext(),"No Changes");
//                Toast.makeText(getContext(), "No Changes", Toast.LENGTH_SHORT).show();
            }else {
                uploadDisplayName(emailToServer, displayName);
            }
        }

    }

    /**
     * Uploads the changed name or the uri or both
     *
     * @param email
     * @param displayName
     */
    private void uploadDisplayName(String email, String displayName) {

        String Url_update = "https://shareeasyapp.000webhostapp.com/UpdateDisplayName.php";
        progressDialog = ProgressDialog.show(getContext(),"Updating Display Name","Please Wait",false,false);
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                String[] field = new String[2];
                field[0] = "email";
                field[1] = "display_name";

                String[] data = new String[2];
                data[0] = email;
                data[1] = displayName;
                PutData putData = new PutData(Url_update, "POST", field, data);

                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        progressDialog.dismiss();
                        String result = putData.getResult();
                        String search = "Your Name Has Been Uploaded.";
                        String search2 = "Failed";
                        String error = "error occurred";
                        String exist = "Email does not exist";
                        if (result.toLowerCase().contains(exist.toLowerCase())) {
                            Toast.makeText(getContext(), exist, Toast.LENGTH_SHORT).show();
                        } else if (result.toLowerCase().contains(search.toLowerCase())) {
                            UIHelpers.status_dialog(getContext(),search);


//                            Toast.makeText(getContext(), search, Toast.LENGTH_SHORT).show();
                            //sp.edit().putString("display_name", displayName).apply();
                            sp = getContext().getSharedPreferences("display_name", Context.MODE_PRIVATE);
                            sp.edit().putString("display_name", displayName).apply();
                        }else  if(result.toLowerCase().contains(search2.toLowerCase())) {
                            //}else  if(result == "2"){
                            UIHelpers.status_dialog(getContext(),search2);
//                            Toast.makeText(getContext(), search2, Toast.LENGTH_LONG).show();

                        }else  if(result.toLowerCase().contains(error.toLowerCase())) {
                            //}else  if(result == "3"){
                            UIHelpers.status_dialog(getContext(),"Error Occurred, Try again...");
//                            Toast.makeText(getContext(), "Error Occurred, Try again...", Toast.LENGTH_LONG).show();

                        }else {
                            UIHelpers.status_dialog(getContext(),result);
//                            Toast.makeText(getContext(), result, Toast.LENGTH_SHORT).show();
                        }
                    }
                }

            }
        });

    }

    /**
     * Opens the camera intent for taking picture
     */
    private void takePicture() {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        ComponentName componentName = intent.resolveActivity(getActivity().getPackageManager());

        if (componentName != null) {
            startActivityForResult(intent, IMAGE_CAPTURE_RC);
        }

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(com.project.ShareEasy.ui.fragments.profile.SetupAccountViewModel.class);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_OK) {
            if (requestCode == IMAGE_CAPTURE_RC) {

                //Getting the image from extras sets it to a global bitmap and to the avatar image view
                bitmap = (Bitmap) data.getExtras().get("data");
                avatarCIV.setImageBitmap(bitmap);

                //Changed the flag of whether the image has changed or not
                avatarHasChanged = true;

            }
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        //Initializes firebase instances
//        mAuth = FirebaseAuth.getInstance();
//        mStorage = FirebaseStorage.getInstance();
//
//        currentUser = mAuth.getCurrentUser();
    }

    ///////////////////////////////////////////////////here
    // Upload captured image online on server function.
    public void ImageUploadToServerFunction(){
        //Toast.makeText(getContext(), "MaiTo FN:"+emailToServer, Toast.LENGTH_SHORT).show();

        ByteArrayOutputStream byteArrayOutputStreamObject ;

        byteArrayOutputStreamObject = new ByteArrayOutputStream();

        // Converting bitmap image to jpeg format, so by default image will upload in jpeg format.
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStreamObject);

        Glide.with(this)
                .load(bitmap)
                .centerCrop()
                .placeholder(R.drawable.ic_account_circle_white)
                .into(avatarCIV);

        byte[] byteArrayVar = byteArrayOutputStreamObject.toByteArray();

        final String ConvertImage = Base64.encodeToString(byteArrayVar, Base64.DEFAULT);

        class AsyncTaskUploadClass extends AsyncTask<Void,Void,String> {

            @Override
            protected void onPreExecute() {

                super.onPreExecute();

                // Showing progress dialog at image upload time.
                progressDialog = ProgressDialog.show(getContext(),"Image is Uploading","Please Wait",false,false);
            }

            @Override
            protected void onPostExecute(String string1) {

                super.onPostExecute(string1);

                // Dismiss the progress dialog after done uploading.
                progressDialog.dismiss();

                String search = "Your Image Has Been Uploaded.";

                String error = "Failed";

                String search1 = "Not Uploaded";


                if (string1.toLowerCase().contains(search.toLowerCase())) {
                    //update boolean avatar
                    sp = getContext().getSharedPreferences("ImgUrlHasChanged", Context.MODE_PRIVATE);
                    sp.edit().putBoolean("ImgUrlHasChanged", true).apply();
                    UIHelpers.status_dialog(getContext(),"Details has been Updated, close the app and Launch again");
//                    Toast.makeText(getContext(), "Details has been Updated, close the app and reload again", Toast.LENGTH_SHORT).show();
                    ress.setText(R.string.UpdateSuccess);
                    Context context = getContext();


                        try {
                            File dir = context.getCacheDir();
                            deleteDir(dir);
                        } catch (Exception e) { e.printStackTrace();}



                }else if (string1.toLowerCase().contains(error.toLowerCase())) {
                    UIHelpers.status_dialog(getContext(),"Some error occured, Try again later.");
//                  Toast.makeText(getContext(), "Some error occured, Try again later.", Toast.LENGTH_SHORT).show();
//                    ress.setText(R.string.UpdateFail);
                }else if (string1.toLowerCase().contains(search1.toLowerCase())) {
                    UIHelpers.status_dialog(getContext(),""+R.string.UploadFailed);
                    Toast.makeText(getContext(), search1, Toast.LENGTH_SHORT).show();
//                    ress.setText(R.string.UploadFailed);
                }else {
                    UIHelpers.status_dialog(getContext(),"Some error Occured, Contact Customer Care");
//                    Toast.makeText(getContext(), "Some error Occured, Contact Customer Care", Toast.LENGTH_SHORT).show();
                }


                    // Printing uploading success message coming from server on android app.
//                UIHelpers.status_dialog(getContext(),string1);
//                Toast.makeText(getContext(),string1,Toast.LENGTH_LONG).show();


                // Setting image as transparent after done uploading.
//                ImageViewHolder.setImageResource(android.R.color.transparent);


            }

            @Override
            protected String doInBackground(Void... params) {

                ImageProcessClass imageProcessClass = new ImageProcessClass();

                HashMap<String,String> HashMapParams = new HashMap<String,String>();

                HashMapParams.put(EmailName, emailToServer);

                HashMapParams.put(DisplayName, displayName);

                HashMapParams.put(ImagePathFieldOnServer, ConvertImage);

                String FinalData = imageProcessClass.ImageHttpRequest(ImageUploadPathOnSever, HashMapParams);

                return FinalData;
            }
        }
        AsyncTaskUploadClass AsyncTaskUploadClassOBJ = new AsyncTaskUploadClass();

        AsyncTaskUploadClassOBJ.execute();
    }

    private boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
            return dir.delete();
        } else if(dir!= null && dir.isFile()) {
            return dir.delete();
        } else {
            return false;
        }
    }

    public class ImageProcessClass{

        public String ImageHttpRequest(String requestURL,HashMap<String, String> PData) {

            StringBuilder stringBuilder = new StringBuilder();

            try {

                URL url;
                HttpURLConnection httpURLConnectionObject ;
                OutputStream OutPutStream;
                BufferedWriter bufferedWriterObject ;
                BufferedReader bufferedReaderObject ;
                int RC ;

                url = new URL(requestURL);

                httpURLConnectionObject = (HttpURLConnection) url.openConnection();

                httpURLConnectionObject.setReadTimeout(19000);

                httpURLConnectionObject.setConnectTimeout(19000);

                httpURLConnectionObject.setRequestMethod("POST");

                httpURLConnectionObject.setDoInput(true);

                httpURLConnectionObject.setDoOutput(true);

                OutPutStream = httpURLConnectionObject.getOutputStream();

                bufferedWriterObject = new BufferedWriter(

                        new OutputStreamWriter(OutPutStream, "UTF-8"));

                bufferedWriterObject.write(bufferedWriterDataFN(PData));

                bufferedWriterObject.flush();

                bufferedWriterObject.close();

                OutPutStream.close();

                RC = httpURLConnectionObject.getResponseCode();

                if (RC == HttpsURLConnection.HTTP_OK) {

                    bufferedReaderObject = new BufferedReader(new InputStreamReader(httpURLConnectionObject.getInputStream()));

                    stringBuilder = new StringBuilder();

                    String RC2;

                    while ((RC2 = bufferedReaderObject.readLine()) != null){

                        stringBuilder.append(RC2);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return stringBuilder.toString();
        }

        private String bufferedWriterDataFN(HashMap<String, String> HashMapParams) throws UnsupportedEncodingException {

            StringBuilder stringBuilderObject;

            stringBuilderObject = new StringBuilder();

            for (Map.Entry<String, String> KEY : HashMapParams.entrySet()) {

                if (check)

                    check = false;
                else
                    stringBuilderObject.append("&");

                stringBuilderObject.append(URLEncoder.encode(KEY.getKey(), "UTF-8"));

                stringBuilderObject.append("=");

                stringBuilderObject.append(URLEncoder.encode(KEY.getValue(), "UTF-8"));
            }

            return stringBuilderObject.toString();
        }

    }

}
