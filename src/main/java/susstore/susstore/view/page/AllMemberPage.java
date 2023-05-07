package susstore.susstore.view.page;

import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import susstore.susstore.view.PageType;
import susstore.susstore.view.component.MemberCardComponent;
import susstore.susstore.view.component.JoinDataTest;
import susstore.susstore.controller.UserController;
import susstore.susstore.controller.UpdateMemberController;
import susstore.susstore.models.Customer;
import susstore.susstore.models.Member;
import susstore.susstore.Subscriber;

public class AllMemberPage extends Page implements Subscriber{
    private final SplitPane pageRootLayout;
    private UserController customerController;
    private UpdateMemberController updateMemberController;
    private TextField nameInput;
    private TextField phoneNumberInput;
    private ChoiceBox membershipChoices;

    public AllMemberPage(UserController customerController) {
        super(PageType.AllMemberPage);
        this.pageRootLayout = new SplitPane();
        this.customerController = customerController;
        
        this.tab.setContent(this.pageRootLayout);
        this.customerController.addSubscriber(null);
        this.updateMemberController = new UpdateMemberController();
        this.updateMemberController.addSubscriber(this);
        loadUI();
        setStylesheet();
    }

    public void update(String s){
        if(s=="set-choosen-member"){
            this.nameInput.setText(updateMemberController.getChoosenMember().getNama());
            this.phoneNumberInput.setText(updateMemberController.getChoosenMember().getNoTelp());
        }
        else{
        VBox customersContainer = new VBox();
        customerController.addCustomer(new Customer());
        for(Member m : customerController.getMembers()){
            MemberCardComponent card = new MemberCardComponent(m,updateMemberController);
            customersContainer.getChildren().add(card.getComponent());
        }
        }
    }

    private void loadUI() {
        VBox customersContainer = new VBox();
        customerController.addMember(new Member(new Customer(),"test","08124"));
        for(Member m : customerController.getMembers()){
            MemberCardComponent card = new MemberCardComponent(m,updateMemberController);
            customersContainer.getChildren().add(card.getComponent());
        }
        
        customersContainer.getStyleClass().add("customers-container-all-customer");

        ScrollPane customersContainerScroll = new ScrollPane();
        customersContainerScroll.setContent(customersContainer);
        customersContainerScroll.setFitToWidth(true);

        Label nameLabel = new Label("Name:");
        nameInput = new TextField();
        VBox nameContainer = new VBox();
        nameLabel.getStyleClass().add("input-label-all-customer");
        nameInput.getStyleClass().add("input-all-customer");
        nameContainer.getChildren().addAll(nameLabel, nameInput);

        Label phonNumberLabel = new Label("Phone Number:");
        phoneNumberInput = new TextField();
        VBox phoneNumberContainer = new VBox();
        phonNumberLabel.getStyleClass().add("input-label-all-customer");
        phoneNumberInput.getStyleClass().add("input-all-customer");
        phoneNumberContainer.getChildren().addAll(phonNumberLabel, phoneNumberInput);

        Label membershipLabel = new Label("Membership:");
        membershipChoices = new ChoiceBox();
        membershipChoices.getItems().add("VIP");
        membershipChoices.getItems().add("MEMBER");
        VBox membershipInputContainer = new VBox();
        membershipLabel.getStyleClass().add("input-label-all-customer");
        membershipChoices.getSelectionModel().selectFirst();
        membershipInputContainer.getChildren().addAll(membershipLabel, membershipChoices);

        Label statusLabel = new Label("Status:");
        ChoiceBox statusChoices = new ChoiceBox();
        statusChoices.getItems().add("ACTIVE");
        statusChoices.getItems().add("DISABLED");
        VBox statusInputContainer = new VBox();
        statusLabel.getStyleClass().add("input-label-all-customer");
        statusChoices.getSelectionModel().selectFirst();
        statusInputContainer.getChildren().addAll(statusLabel, statusChoices);

        BorderPane membershipAndStatusContainer = new BorderPane();
        membershipAndStatusContainer.setLeft(membershipInputContainer);
        membershipAndStatusContainer.setRight(statusInputContainer);
//        membershipAndStatusContainer.getChildren().addAll(membershipInputContainer, statusInputContainer);
        membershipAndStatusContainer.getStyleClass().add("membership-status-container-all-customer");

        VBox formContainer = new VBox();
        formContainer.getStyleClass().add("form-container-all-customer");
        formContainer.getChildren().addAll(nameContainer, phoneNumberContainer, membershipAndStatusContainer);

        Button cancelButton = new Button("Cancel");
        Button saveButton = new Button("Save");
        HBox actionButtonsContainer = new HBox();
        cancelButton.getStyleClass().addAll("action-button-all-customer", "cancel-button-all-customer");
        saveButton.getStyleClass().addAll("action-button-all-customer", "save-button-all-customer");
        actionButtonsContainer.getStyleClass().add("action-buttons-container-all-customer");
        actionButtonsContainer.getChildren().addAll(cancelButton, saveButton);

        saveButton.setOnAction(event -> {
            
        });

        BorderPane formAndActionsContainer = new BorderPane();
        formAndActionsContainer.setCenter(formContainer);
        formAndActionsContainer.setBottom(actionButtonsContainer);
        formAndActionsContainer.getStyleClass().add("form-actions-container-all-customer");

        this.pageRootLayout.setDividerPositions(0.65, 0.35);
        this.pageRootLayout.getItems().addAll(customersContainerScroll, formAndActionsContainer);
    }


    private void setStylesheet() {
        this.pageRootLayout.getStyleClass().add("page-root-layout");
        this.pageRootLayout.getStylesheets().add("css/all-customer-page.css");
    }
}