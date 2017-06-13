package com.wz.beijingnews.ui.adapter;

import android.view.View;
import android.widget.CheckBox;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wz.beijingnews.R;
import com.wz.beijingnews.bean.FileEntry;

/**
 * Created by wz on 17-6-9.
 */

public class CatalogAdapter extends BaseQuickAdapter<FileEntry,BaseViewHolder> {
    public CatalogAdapter() {
        super(R.layout.item_catalog);
    }

    @Override
    protected void convert(BaseViewHolder helper, FileEntry item) {
        if (item.isFile()){
            helper.getView(R.id.tv_file_type).setBackgroundResource(R.drawable.file_icon_default);
            helper.getView(R.id.cb_file).setVisibility(View.VISIBLE);
            helper.getView(R.id.iv_next_catalog).setVisibility(View.GONE);

            final CheckBox view = helper.getView(R.id.cb_file);
            view.setChecked(item.isCheck());



//            helper.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    if (view.isChecked()){
//                        view.setChecked(false);
//                    } else {
//                        view.setChecked(true);
//                    }
//                }
//            });

        } else {
            helper.getView(R.id.tv_file_type).setBackgroundResource(R.drawable.folder_icon_default);
            helper.getView(R.id.cb_file).setVisibility(View.GONE);
            helper.getView(R.id.iv_next_catalog).setVisibility(View.VISIBLE);

            helper.setText(R.id.tv_child_num,item.getChildSize()+"项");
//            helper.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    ToastUtil.show("点击了");
//                }
//            });

        }
        String path = item.getPath();
        // Log.e("TAG",path.substring(path.lastIndexOf("/")));
        helper.setText(R.id.tv_catalog,path.substring(path.lastIndexOf("/")+1));
    }
}
