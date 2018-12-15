package pl.codeme.jeeb.e2.bank;

import static pl.codeme.jeeb.e2.bank.ATMController.Action.AUTH_CN;
import static pl.codeme.jeeb.e2.bank.ATMController.Action.AUTH_PIN;
import static pl.codeme.jeeb.e2.bank.ATMController.Action.WITHDRAW;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import pl.codeme.jeeb.e2.bank.services.rest.data.CardInfo;

public class ATMController {

    public ATMController() {
    }

    public enum Action {AUTH_CN, AUTH_PIN, WITHDRAW}

    @FXML
    private TextArea messages;
    @FXML
    private TextField cmd;
    private ATMClient atm;
    private CardInfo ci;
    private Action[] actions = {AUTH_CN, AUTH_PIN, WITHDRAW};
    private byte ix = 0;

    @FXML
    public void initialize() {
        messages.appendText("Dzień dobry CODE:ME\n");
        messages.appendText("Podaj numer karty");

        atm = new ATMClient("http://localhost:8080/bank-services/rest");
    }

    @FXML
    public void send() {
        Action action = actions[ix++];
        switch (action) {
            case AUTH_CN:
                atm.getServicesProxy().authCardNumber(cmd.getText());
                messages.appendText("\nCN: " + cmd.getText());
                messages.appendText("\nPodaj PIN:");
                break;
            case AUTH_PIN:
                String token = atm.getServicesProxy().authPin(cmd.getText()).getHeaderString("token");
                messages.appendText("\nPIN: ******");
                atm.setToken(token);
                ci = atm.getServicesProxy().getCardInfo().readEntity(CardInfo.class);
                messages.appendText("\nKarta " + ci.getCardNumber() + "\n Konto: " + ci.getAccountNumber() + ": " + ci.getAmount());
                messages.appendText("\nPodaj kwotę:");
                break;
            case WITHDRAW:
                atm.getServicesProxy().withdraw(Integer.valueOf(cmd.getText()));
                messages.appendText("\nKarta " + ci.getCardNumber() + "\n Konto: " + ci.getAccountNumber() + ": " + ci.getAmount());
                messages.appendText("\nDowidzenia");
                atm.reset();
                ix = 0;
                messages.appendText("Podaj numer karty");
                break;
        }
        cmd.clear();
    }

}
