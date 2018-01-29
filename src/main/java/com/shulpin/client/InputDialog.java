package com.shulpin.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;

public class InputDialog extends DialogBox {

    private DialogBoxOpener opener = null;
    private TextArea textArea;
    private Button ok;

    public InputDialog(){
        setText("Message");
        setModal(true);
        // Enable animation.
        setAnimationEnabled(true);
        textArea=new TextArea();
        ok = new Button("send");
        ok.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                InputDialog.this.hide();
                if (opener != null)
                    opener.dialogBoxCancelled();
            }
        });
        VerticalPanel verticalPanel = new VerticalPanel();
        verticalPanel.add(textArea);
        int windowHeight = Window.getClientHeight();
        int windowWidth = Window.getClientWidth();
        textArea.setSize(0.3*windowWidth+"px",0.3*windowHeight+"px");
        verticalPanel.add(ok);

        setWidget(verticalPanel);
    }

    public TextArea getTextArea() {
        return textArea;
    }

    public void setOpener(DialogBoxOpener opener) {
        this.opener = opener;
    }
}
