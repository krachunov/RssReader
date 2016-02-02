/**
 * @author Kristina
 */
package edu.pragmatic.java.advanced.rss.project.UI;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.pragmatic.java.advanced.rss.project.model.MyRssReader;
import edu.pragmatic.java.advanced.rss.project.model.RssInfo;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class StartDisplay extends Application {
	private Scene scene;
	private BorderPane root;
	private VBox leftMenu;
	private HBox buttonsPane;
	private TextArea textFeed;
	private Button backBtn;
	private Button forwardBtn;
	private TextField addField;
	private Button deleteBtn;
	private Button openLinkBtn;
	private Map<String, List<RssInfo>> allSources;
	private ListView sourcesObservable;
	private ListView newsForSourceObservable;
	
	private List<RssInfo> currenFeeds;
	
	///по някаква причина не иска да ми cast-не set към list затова:
	private List<String> convertor(Set<String> current){
		List<String> toReturn = new LinkedList<>();
		toReturn.addAll(current);
		return toReturn;
	}
	
	public Scene getScene() {
		return scene;
	}
	public void setScene(Scene scene) {
		this.scene = scene;
	}
	public BorderPane getRoot() {
		return root;
	}
	public void setRoot(BorderPane root) {
		this.root = root;
	}
	public VBox getLeftMenu() {
		return leftMenu;
	}
	public void setLeftMenu(VBox leftMenu) {
		this.leftMenu = leftMenu;
	}
	public HBox getButtonsPane() {
		return buttonsPane;
	}
	public void setButtonsPane(HBox buttonsPane) {
		this.buttonsPane = buttonsPane;
	}
	public TextArea getTextFeed() {
		return textFeed;
	}
	public void setTextFeed(TextArea textFeed) {
		this.textFeed = textFeed;
	}
	public Button getBackBtn() {
		return backBtn;
	}
	public void setBackBtn(Button backBtn) {
		this.backBtn = backBtn;
	}
	public Button getForwardBtn() {
		return forwardBtn;
	}
	public void setForwardBtn(Button forwardBtn) {
		this.forwardBtn = forwardBtn;
	}
	public TextField getAddField() {
		return addField;
	}
	public void setAddField(TextField addField) {
		this.addField = addField;
	}
	public Button getDeleteBtn() {
		return deleteBtn;
	}
	public void setDeleteBtn(Button deleteBtn) {
		this.deleteBtn = deleteBtn;
	}
	public Button getOpenLinkBtn() {
		return openLinkBtn;
	}
	public void setOpenLinkBtn(Button openLinkBtn) {
		this.openLinkBtn = openLinkBtn;
	}
	public Map<String, List<RssInfo>> getAllSources() {
		return allSources;
	}
	public void setAllSources(Map<String, List<RssInfo>> allSources) {
		this.allSources = allSources;
	}
	public ListView getSourcesObservable() {
		return sourcesObservable;
	}
	public void setSourcesObservable(ListView sourcesObservable) {
		this.sourcesObservable = sourcesObservable;
	}
	public ListView getNewsForSourceObservable() {
		return newsForSourceObservable;
	}
	public void setNewsForSourceObservable(ListView newsForSourceObservable) {
		this.newsForSourceObservable = newsForSourceObservable;
	}
	public List<RssInfo> getCurrenFeeds() {
		return currenFeeds;
	}
	public void setCurrenFeeds(List<RssInfo> currenFeeds) {
		this.currenFeeds = currenFeeds;
	}

	@SuppressWarnings("unchecked")
	private void startScene() {
		root = new BorderPane();
        leftMenu = new VBox(5);
        leftMenu.setId("menu");
        backBtn = new Button();
        backBtn.setGraphic(new ImageView(new Image(new File("back.png").toURI().toString())));
        forwardBtn = new Button();
        forwardBtn.setGraphic(new ImageView(new Image(new File("forward.png").toURI().toString())));
        deleteBtn = new Button();
        deleteBtn.setGraphic(new ImageView(new Image(new File("delete.png").toURI().toString())));
        openLinkBtn = new Button();
        openLinkBtn.setGraphic(new ImageView(new Image(new File("link.png").toURI().toString())));   
     
        buttonsPane = new HBox(10);
        buttonsPane.setId("buttons-pane");
        buttonsPane.getChildren().addAll(backBtn,forwardBtn, openLinkBtn, deleteBtn);
        
        Label label = new Label("Add new RSS");
        addField = new TextField();
        
        leftMenu.getChildren().add(buttonsPane);
        leftMenu.getChildren().addAll(label, addField);
        
        root.setLeft(leftMenu);
        textFeed = new TextArea("Welcome! \n\n This is the best RSS Reader!!!");
        textFeed.setId("text-feed");
        textFeed.setEditable(false);
        root.setCenter(textFeed);
        MyRssReader reader = new MyRssReader();
        reader.addFeedSorce("https://sports.yahoo.com/top/rss.xml");
        reader.addFeedSorce("https://sports.yahoo.com/golf/blog/devil_ball_golf/rss.xml");
		reader.addFeedSorce("http://rss.cnn.com/rss/edition.rss");
		allSources = FXCollections.observableMap(reader.getAllSources());
		sourcesObservable = new ListView(FXCollections.observableList(convertor(allSources.keySet())));
		newsForSourceObservable = new ListView<>();
		newsForSourceObservable.setId("list-view");
		sourcesObservable.setId("list-view");
		leftMenu.getChildren().addAll(new Label("Your RSS sources"), sourcesObservable);
	    scene = new Scene(root, 1000, 800);
	    
	    addField.setOnKeyPressed(new EventHandler<KeyEvent>()
	    {
	        @Override
	        public void handle(KeyEvent ke)
	        {
	            if (ke.getCode().equals(KeyCode.ENTER))
	            {
	            	String tmp = addField.getText();
	            	//allSources.put(..., ...);
	            	// на мястото на многоточието според мен трябва да има controller.нещо(), което нещо именно прави
	            	// функциите от MyRssReader addFeedSorce(съответно иcreateFeed,createRssInfoObject).
	           //защото нали идеята е да променим оbservableMap-a, а иначе не виждам това как ще стане...то и така не ми е много ясно, ама нещо
	            	//такова ми се върти в главата
	            }
	        }
	    });
	    
	   deleteBtn.setOnAction(new EventHandler<ActionEvent>() {
		   
           @Override
           public void handle(ActionEvent event) {
        	   Object tmp = sourcesObservable.getSelectionModel().getSelectedItem();
        	   //така сме взели елемента от sourcesObservable, който искаме да изтрием
        	   //трябва да видим как да го преманем от въпросния sourceObservable
        	   //и после как да премахнем елемента с key = tmp от map-a allSources,
        	   //което трябва да се отрази и на map-a в reader-a, щото нали е оbservable
           }
       });
	   sourcesObservable.setOnMouseClicked(new EventHandler<MouseEvent>() {
	        @Override
	        public void handle(MouseEvent event) {
	        	//currenFeeds =  онзи List<RssInfo> от allSources, чийто key = sourcesObservable.getSelectionModel().getSelectedItem()
	           //newsForSourceObservable =  ListView(FXCollections.observableList(tmp.foreach(t-> t.getTitle())); 
	        	//leftMenu.getChildren().remove(sourcesObservable);
	        	//leftMenu.getChildren().add(newsForSourceObservable);
	        }
	    });
	   newsForSourceObservable.setOnMouseClicked(new EventHandler<MouseEvent>() {
	        @Override
	        public void handle(MouseEvent event) {
	           //textFeed.setText(...);
	        	//... e по някакъв начин String със сложена информацията от 
	        	//controller.нещо(), което нещо именно връща
	        	//този RssInfo: currentFeeds.stream().filter(cf -> cf.getTitle().equals(newsForSourceObservable.getSelectionModel().getSelectedItem()))
	        }
	    });
	   backBtn.setOnAction(new EventHandler<ActionEvent>() {
		
		@Override
		public void handle(ActionEvent event) {
			//textFeed.setText(...);
        	//... e по някакъв начин String със сложена информацията от 
			//controller.нещо(), което нещо именно връща
        	//предния на този RssInfo:  currentFeeds.stream().filter(cf -> cf.getTitle().equals(newsForSourceObservable.getSelectionModel().getSelectedItem()))
		}
	});
	   forwardBtn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				//textFeed.setText(...);
	        	//... e по някакъв начин String със сложена информацията от 
				//controller.нещо(), което нещо именно връща
	        	//следващия на този RssInfo:  currentFeeds.stream().filter(cf -> cf.getTitle().equals(newsForSourceObservable.getSelectionModel().getSelectedItem()))
			}
		});
	   
	   openLinkBtn.setOnAction(new EventHandler<ActionEvent>() {
		   
		   	//final WebView browser = new WebView();
		   // final WebEngine webEngine = browser.getEngine();
		    // webEngine.load(... .getURL()?);
		    // ... е controller.нещо(), което нещо именно връща
		    // този RssInfo: currentFeeds.stream().filter(cf -> cf.getTitle().equals(newsForSourceObservable.getSelectionModel().getSelectedItem()))
		@Override
		public void handle(ActionEvent event) {
			//root.setCenter(browser);	
		}
	});
	   
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("RSS Reader");
        startScene();
        scene.getStylesheets().add(this.getClass().getResource("display.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
	}
	
}
