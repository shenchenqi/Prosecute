package com.micro.root.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import java.util.ArrayList;

/**
 * @Description: BaseDialog
 * @Author: ALin
 * @CreateDate: 19-9-12
 */
public abstract class BaseDialog<M> {
    private final AlertDialog alertDialog;
    private final AlertDialog.Builder builder;
    protected final Context context;

    protected BaseDialog(Context context) {
        this.context = context;
        builder = new AlertDialog.Builder(context);

        String title = getTitle();
        if (!TextUtils.isEmpty(title)) {
            builder.setTitle(title);
        }
        int icon = getIcon();
        if (icon != 0) {
            builder.setIcon(icon);
        }
        String message = getMessage();
        if (!TextUtils.isEmpty(message)) {
            builder.setMessage(message);
        }

        final ArrayList<String> items = getItems();
        if (items != null && !items.isEmpty() && arrayInterface != null) {
            String[] strings = new String[items.size()];
            items.toArray(strings);
            builder.setItems(strings, (dialog, which) -> arrayInterface.onItemClick(items.get(which), which));
        }

        final ArrayList<String> multiChoiceItems = getMultiChoiceItems();
        if (multiChoiceItems != null && !multiChoiceItems.isEmpty() && multiChoiceInterface != null) {
            String[] strings = new String[multiChoiceItems.size()];
            multiChoiceItems.toArray(strings);
            final boolean[] booleans = new boolean[multiChoiceItems.size()];
            booleans[0] = true;
            builder.setMultiChoiceItems(strings, booleans, (dialog, which, isChecked) -> multiChoiceInterface.onItemCheckedClick(multiChoiceItems.get(which), which, isChecked));
        }

        final ArrayList<String> singleChoiceItems = getSingleChoiceItems();
        if (singleChoiceItems != null && !singleChoiceItems.isEmpty() && singleChoiceInterface != null) {
            String[] strings = new String[singleChoiceItems.size()];
            singleChoiceItems.toArray(strings);
            builder.setSingleChoiceItems(strings, 0, (dialog, which) -> singleChoiceInterface.onItemSingleClick(singleChoiceItems.get(which), which));
        }

        String neutral = getNeutralButton();
        if (!TextUtils.isEmpty(neutral) && neutralInterface != null) {
            builder.setNeutralButton(neutral, (dialog, which) -> neutralInterface.onNeutralClick());
        }

        String positive = getPositiveButton();
        if (!TextUtils.isEmpty(positive) && positiveInterface != null) {
            builder.setPositiveButton(positive, (dialog, which) -> positiveInterface.onPositiveClick());
        }

        String negative = getNegativeButton();
        if (!TextUtils.isEmpty(negative) && negativeInterface != null) {
            builder.setNegativeButton(negative, (dialog, which) -> negativeInterface.onNegativeClick());
        }

        int layoutId = getLayoutId();
        if (layoutId != 0) {
            View view = View.inflate(context, layoutId, null);
            builder.setView(view);
            initView(view);
        }

        builder.setCancelable(isCancelable());
        alertDialog = builder.create();
    }

    protected abstract boolean isCancelable();

    protected String getTitle() {
        return null;
    }//标题

    protected int getIcon() {
        return 0;
    }//图标

    protected String getMessage() {
        return null;
    }//内容

    protected int getLayoutId() {
        return 0;
    }//自定义

    protected void initView(View view) {
    }

    protected void initData() {
    }

    protected void initData(M data) {
    }

    protected ArrayList<String> getItems() {
        return null;
    }//列表框

    private ArrayInterface arrayInterface;

    public void setArrayInterface(ArrayInterface arrayInterface) {
        this.arrayInterface = arrayInterface;
    }

    protected ArrayList<String> getMultiChoiceItems() {
        return null;
    }//复选列表框

    private MultiChoiceInterface multiChoiceInterface;

    public void setMultiChoiceInterface(MultiChoiceInterface multiChoiceInterface) {
        this.multiChoiceInterface = multiChoiceInterface;
    }

    protected ArrayList<String> getSingleChoiceItems() {
        return null;
    }//单选列表框

    private SingleChoiceInterface singleChoiceInterface;

    public void setSingleChoiceInterface(SingleChoiceInterface singleChoiceInterface) {
        this.singleChoiceInterface = singleChoiceInterface;
    }

    protected String getNeutralButton() {
        return null;
    }//普通按钮

    private NeutralInterface neutralInterface;

    public void setNeutralInterface(NeutralInterface neutralInterface) {
        this.neutralInterface = neutralInterface;
    }

    protected String getPositiveButton() {
        return null;
    }//"Yes"按钮

    private PositiveInterface positiveInterface;

    public void setPositiveInterface(PositiveInterface positiveInterface) {
        this.positiveInterface = positiveInterface;
    }

    protected String getNegativeButton() {
        return null;
    }//"No"按钮

    private NegativeInterface negativeInterface;

    public void setNegativeInterface(NegativeInterface negativeInterface) {
        this.negativeInterface = negativeInterface;
    }

    public boolean isShowing() {
        return alertDialog.isShowing();
    }

    public void showDialog() {
        alertDialog.show();
    }

    public void hideDialog() {
        alertDialog.dismiss();
    }

    public interface ArrayInterface {
        void onItemClick(String value, int position);
    }

    public interface MultiChoiceInterface {
        void onItemCheckedClick(String value, int position, boolean isChecked);
    }

    public interface SingleChoiceInterface {
        void onItemSingleClick(String value, int position);
    }

    public interface NeutralInterface {
        void onNeutralClick();
    }

    public interface PositiveInterface {
        void onPositiveClick();
    }

    public interface NegativeInterface {
        void onNegativeClick();
    }
}