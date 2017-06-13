package com.wz.beijingnews.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wz.beijingnews.R;
import com.wz.beijingnews.bean.FileEntry;
import com.wz.beijingnews.ui.adapter.CatalogAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.wz.beijingnews.ui.fragment.VideoFragment.REQUESTCODE;

/**
 * Created by wz on 17-6-8.
 */

public class SearchFileActivity extends BaseActivity {


    @BindView(R.id.tv_choice)
    TextView mTvChoice;
    @BindView(R.id.tv_storage_location)
    TextView mTvStorageLocation;
    @BindView(R.id.recycler_view_catalog)
    RecyclerView mRecyclerViewCatalog;
    @BindView(R.id.btn_commit)
    Button mBtnCommit;
    private CatalogAdapter mAdapter;
    private int mSelectedNum;
    private final ArrayList<FileEntry> mFiles;

    public SearchFileActivity() {
        mFiles = new ArrayList<>();
    }

    @Override
    protected int setLayoutRedID() {
        return R.layout.activity_search_local_file;
    }

    @Override
    protected void init() {

        mRecyclerViewCatalog.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new CatalogAdapter();
        File storageDirectory = Environment.getExternalStorageDirectory();

        getCatalog(storageDirectory);

        initListener();
    }

    private void initListener() {
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                FileEntry item = (FileEntry) adapter.getItem(position);
                if (item.isFile()) {
                    CheckBox checkBox = (CheckBox) view.findViewById(R.id.cb_file);
                    if (checkBox.isChecked()) {
                        item.setCheck(false);
                        checkBox.setChecked(false);
                        if (mSelectedNum>0){
                            mSelectedNum--;
                            mTvChoice.setText("已选择"+mSelectedNum+"个");
                            mFiles.remove(item);
                        }
                    } else {
                        checkBox.setChecked(true);
                        item.setCheck(true);
                        mSelectedNum++;
                        mFiles.add(item);
                        mTvChoice.setText("已选择"+mSelectedNum+"个");
                    }
                } else {
                    File file = new File(item.getPath());
                    String[] list = file.list();
                    if (list.length > 0) {
                        mSelectedNum=0;
                        mTvChoice.setText("未选择");
                        mTvStorageLocation.setText(item.getPath());
                        getCatalog(new File(item.getPath()));
                        mFiles.clear();
                    } else {
                        Toast.makeText(SearchFileActivity.this, "此目录没有文件", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        mBtnCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("files",mFiles);
                intent.putExtras(bundle);
                setResult(REQUESTCODE,intent);
                finish();
            }
        });
    }

    public void getCatalog(File file) {
        //File storageDirectory = Environment.getExternalStorageDirectory();
        File[] files = file.listFiles();
        List<FileEntry> fileEntries = new ArrayList<>();
        FileEntry fileEntry ;
        for (int i = 0; i < files.length; i++) {
            fileEntry = new FileEntry();
            if (files[i].isFile()){
                fileEntry = new FileEntry();
                fileEntry.setFile(true);
                fileEntry.setPath(files[i].getPath());
                fileEntry.setParent(files[i].getParent());

                fileEntry.setChildSize(files[i].list()==null?0:files[i].list().length);
            } else if (files[i].isDirectory()){
                fileEntry = new FileEntry();
                fileEntry.setFile(false);
                fileEntry.setPath(files[i].getPath());
                fileEntry.setParent(files[i].getParent());
                fileEntry.setChildSize(files[i].list()==null?0:files[i].list().length);
            }
            fileEntries.add(fileEntry);
        }

        mAdapter.addData(fileEntries);
        mRecyclerViewCatalog.setAdapter(mAdapter);

    }
}
