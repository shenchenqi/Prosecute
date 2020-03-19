package com.micro.root.dialog;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.micro.root.R;


/**
 * @author lun_fang on 2018/6/26.
 */

public class CommonDialog {
    private Dialog build;
    private TextView mTvTitle;
    private EditText mEtName;
    private TextView mTvCancel;
    private TextView mTvConfirm;
    private TextView mTvMsg;
    /**
     * 通过这个参数不让输入框内容为空
     */
    private boolean canEnterNothing = true;
    private String toastTips;
    private boolean isConfirmDismiss = true;
    private Context context;

    private CommonDialog(final Context context) {
        this.context = context;
        if (build != null) {
            build.dismiss();
            build = null;
        }
        build = new Dialog(context, R.style.CustomDialog);
        Window window = build.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        window.setContentView(R.layout.common_dialog);
        build.setCanceledOnTouchOutside(false);
        mEtName = build.findViewById(R.id.et_name);
        mTvCancel = build.findViewById(R.id.tv_cancel);
        mTvMsg = build.findViewById(R.id.tv_msg);
        mTvTitle = build.findViewById(R.id.tv_title);
        mTvConfirm = build.findViewById(R.id.tv_confirm);
        mTvCancel.setOnClickListener(v -> {
            if (dismissListener != null) {
                dismissListener.onNegativeClick();
            }
            build.dismiss();
        });
        mTvConfirm.setOnClickListener(v -> {
            String msg = mEtName.getText().toString().trim();
            if (!canEnterNothing && TextUtils.isEmpty(msg)) {
                if (toastTips != null) {
                    Toast.makeText(context, toastTips, Toast.LENGTH_SHORT).show();
                }
                return;
            }
            if (dismissListener != null) {
                dismissListener.onPositiveClick(msg);
            }

            if (isConfirmDismiss) {
                build.dismiss();
            }
        });
    }

    public void dismiss() {
        build.dismiss();
    }

    public EditText getEditText() {
        return mEtName;
    }

    private void setTitle(String title) {
        mTvTitle.setText(title);
    }

    private void setCanEnterNothing(boolean canEnterNothing) {
        this.canEnterNothing = canEnterNothing;
    }

    private void setToastTips(String toastTips) {
        this.toastTips = toastTips;
    }

    private void setMsg(String msg) {
        mTvMsg.setText(msg);
    }

    private void setEditHint(String hint) {
        mEtName.setHint(hint);
    }

    private void setInputType(int inputType) {
        mEtName.setInputType(inputType);
    }

    private void setEditMaxLength(int maxLength) {
        mEtName.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
    }

    public static Builder builder(Context context) {
        return Builder.get(context);
    }

    public static class Builder {
        private CommonDialog addFriendDialog;

        private Builder(Context context) {
            addFriendDialog = new CommonDialog(context);
        }

        public CommonDialog build() {
            addFriendDialog.show();
            return addFriendDialog;
        }

        public Builder title(String title) {
            addFriendDialog.setTitle(title);
            return this;
        }

        public Builder isConfirmDismiss(boolean isConfirmDismiss) {
            addFriendDialog.isConfirmDismiss = isConfirmDismiss;
            return this;
        }

        public Builder msg(String msg) {
            addFriendDialog.setMsg(msg);
            return this;
        }

        public Builder setDismissListener(BuildDismissListener dismissListener) {
            addFriendDialog.setDismissListener(dismissListener);
            return this;
        }

        public Builder hideEditText() {
            addFriendDialog.hideEditText();
            return this;
        }

        public Builder hideCancel() {
            addFriendDialog.hideCancel();
            return this;
        }

        public Builder hideTitle() {
            addFriendDialog.hideTitle();
            return this;
        }

        public Builder setSureText(int id) {
            addFriendDialog.setSureText(id);
            return this;
        }

        public Builder setSureText(String value) {
            addFriendDialog.setSureText(value);
            return this;
        }

        public Builder setMsgText(int color, int size) {
            addFriendDialog.setMsgText(color, size);
            return this;
        }

        public Builder setEditHint(String hint) {
            addFriendDialog.setEditHint(hint);
            return this;
        }

        public Builder setInputType(int inputType) {
            addFriendDialog.setInputType(inputType);
            return this;
        }

        public Builder setEditMaxLength(int maxLength) {
            addFriendDialog.setEditMaxLength(maxLength);
            return this;
        }

        public Builder setCanEnterNothing(boolean canEnterNothing) {
            addFriendDialog.setCanEnterNothing(canEnterNothing);
            return this;
        }

        public Builder setToastTips(String toastTips) {
            addFriendDialog.setToastTips(toastTips);
            return this;
        }

        public static Builder get(Context context) {
            return new Builder(context);
        }
    }

    private void hideTitle() {
        mTvTitle.setVisibility(View.GONE);
    }

    private void setMsgText(int res, int size) {
        mTvMsg.setTextColor(res);
        mTvMsg.setTextSize(size);
    }

    private void setSureText(int id) {
        mTvConfirm.setText(id);
    }

    private void setSureText(String value) {
        mTvConfirm.setText(value);
    }

    private void hideCancel() {
        mTvCancel.setVisibility(View.GONE);
    }

    private void hideEditText() {
        mEtName.setVisibility(View.GONE);
    }

    private void show() {
        if(context != null && !((Activity) context).isFinishing()) {
            build.show();
        }
    }

    private BuildDismissListener dismissListener;

    private void setDismissListener(BuildDismissListener dismissListener) {
        this.dismissListener = dismissListener;
    }

    public interface BuildDismissListener {
        /**
         * negative
         */
        void onNegativeClick();

        /**
         * positive
         *
         * @param msg msg
         */
        void onPositiveClick(String msg);
    }
}
