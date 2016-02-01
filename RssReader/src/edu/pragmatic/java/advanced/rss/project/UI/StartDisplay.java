/**
 * @author Kristina
 */
package edu.pragmatic.java.advanced.rss.project.UI;

import java.io.File;

import edu.pragmatic.java.advanced.rss.project.model.MyRssReader;
import edu.pragmatic.java.advanced.rss.project.model.RssOption;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
	private ListView sources;
	private ListView newsForSource;
	
	private void startScene() {
		root = new BorderPane();
        leftMenu = new VBox(5);
        leftMenu.setId("menu");
        try {
        backBtn = new Button();
        backBtn.setGraphic(new ImageView(new Image(new File("back.png").toURL().toString())));
        forwardBtn = new Button();
        forwardBtn.setGraphic(new ImageView(new Image(new File("forward.png").toURL().toString())));
        deleteBtn = new Button();
        deleteBtn.setGraphic(new ImageView(new Image(new File("delete.png").toURL().toString())));
        openLinkBtn = new Button();
        openLinkBtn.setGraphic(new ImageView(new Image(new File("link.png").toURL().toString())));
        
        }
        catch (Exception e) {
        	///TODO: printStackTrace in log file
			e.printStackTrace();
		} 
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
        RssOption reader = new MyRssReader();
        reader.addFeedSorce("https://sports.yahoo.com/top/rss.xml");
        reader.addFeedSorce("https://sports.yahoo.com/golf/blog/devil_ball_golf/rss.xml");
		reader.addFeedSorce("http://rss.cnn.com/rss/edition.rss");
		sources = new ListView<String>(FXCollections.observableList(reader.displayAllSources()));
		sources.setId("list-view");
		leftMenu.getChildren().addAll(new Label("Your RSS sources"), sources);
	    scene = new Scene(root, 1000, 800);
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
