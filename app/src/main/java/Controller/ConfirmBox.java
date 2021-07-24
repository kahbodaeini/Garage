package Controller;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class ConfirmBox {
    static boolean answer;
    public static boolean createConfirmBox(Context context, String message){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        builder1.setMessage(message);
        builder1.setCancelable(true);
        final int[] flag = {0};
        final boolean[] isClicked = {false};

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        answer = true;
                        dialog.cancel();
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        answer = false;
                        dialog.cancel();

                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();

        return answer;
    }
}
