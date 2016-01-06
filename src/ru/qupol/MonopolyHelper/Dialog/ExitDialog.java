package ru.qupol.MonopolyHelper.Dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import ru.qupol.Hello2App.R;

/**
 * Created by Pavel on 29.09.2015.
 */
public class ExitDialog {
    private ExitDialog() {
    }

    public static void show(final Activity activity, DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        String exitText = activity.getResources().getString(R.string.ExitDialogText);
        builder.setMessage(exitText);
        String okBtnText = activity.getResources().getString(R.string.OkButtonText);
        String cancelButtonText = activity.getResources().getString(R.string.CancelButtonText);
        builder.setPositiveButton(okBtnText, onClickListener);
        builder.setNegativeButton(cancelButtonText, null);
        builder.show();
    }
}
