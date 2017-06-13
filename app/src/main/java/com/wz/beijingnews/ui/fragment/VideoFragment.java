package com.wz.beijingnews.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.wz.beijingnews.R;
import com.wz.beijingnews.bean.FileEntry;
import com.wz.beijingnews.common.http.ApiService;
import com.wz.beijingnews.di.component.AppComponent;
import com.wz.beijingnews.ui.activity.SearchFileActivity;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wz on 17-6-2.
 */

public class VideoFragment extends BaseFragment {
    @BindView(R.id.et_file_name)
    EditText mEtFileName;
    @BindView(R.id.btn_open_file)
    Button mBtnOpenFile;
    @BindView(R.id.btn_upload)
    Button mBtnUpload;

    public static final int REQUESTCODE = 1;

    @Override
    protected void setupAppComponent(AppComponent appComponent) {

    }

    @Override
    protected int setLayoutResID() {
        return R.layout.fragment_video;
    }

    @OnClick({R.id.et_file_name, R.id.btn_open_file, R.id.btn_upload})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.et_file_name:
                break;
            case R.id.btn_open_file:
                Intent intent = new Intent(getActivity(), SearchFileActivity.class);
                startActivityForResult(intent, REQUESTCODE);
                break;
            case R.id.btn_upload:
                uploadFiles();
                break;
        }
    }

    private void uploadFiles() {
        String path = mEtFileName.getText().toString().trim();
        String[] split = path.split("\r\n");
        for (int i = 0; i < split.length; i++) {

            File file = new File(split[i]);//访问手机端的文件资源，保证手机端sdcdrd中必须有这个文件
            RequestBody requestFile =
                    RequestBody.create(MediaType.parse("multipart/form-data"), file);

            MultipartBody.Part body = MultipartBody.Part.createFormData("aFile", file.getName(), requestFile);

            String descriptionString = "This is a description";
            RequestBody description =
                    RequestBody.create(MediaType.parse("multipart/form-data"), descriptionString);

            Call<ResponseBody> call = service.upload(description, body);

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call,
                                       Response<ResponseBody> response) {
                    System.out.println("success");
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUESTCODE) {
            Bundle extras = data.getExtras();
            mEtFileName.setText("");
            String path = "";
            ArrayList<FileEntry> files = (ArrayList<FileEntry>) extras.getSerializable("files");
            for (int i = 0; i < files.size(); i++) {
                if (i < files.size() - 1) {
                    path += files.get(0).getPath() + "\r\n";
                } else {
                    path += files.get(0).getPath();
                }

                mEtFileName.setText(path);
            }

        }
    }


    Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://192.168.31.12:8080/")
            .build();
    ApiService service = retrofit.create(ApiService.class);


}
