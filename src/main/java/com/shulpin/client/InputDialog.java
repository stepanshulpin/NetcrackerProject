package com.shulpin.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;

public class InputDialog extends DialogBox {

    private DialogBoxOpener opener = null;
    private TextArea textArea;
    private Button ok;
    private Button close;

    public InputDialog(){

        this.removeStyleName("gwt-DialogBox");
        this.addStyleName("dialogBox");
        setText("Message");
        setModal(true);
        // Enable animation.
        setAnimationEnabled(true);
        textArea=new TextArea();
        ok = new Button("Send");
        close= new Button("Close");
        close.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                InputDialog.this.hide();
            }
        });
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
        HorizontalPanel buttons = new HorizontalPanel();
        ok.removeStyleName("gwt-Button");
        close.removeStyleName("gwt-Button");
        ok.addStyleName("dialogButton");
        close.addStyleName("dialogButton");
        buttons.add(ok);
        buttons.add(close);
        verticalPanel.add(buttons);

        setWidget(verticalPanel);
    }

    public TextArea getTextArea() {
        return textArea;
    }

    public void setOpener(DialogBoxOpener opener) {
        this.opener = opener;
    }
}
