package javafxLoginrecCrudForm;



import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class CrudController {

    @FXML
    private TextField crud_StudentNumber;

    @FXML
    private Button crud_addbtn;

    @FXML
    private Button crud_clearbtn;

    @FXML
    private TableColumn<StudentData, String> crud_col_course;

    @FXML
    private TableColumn<StudentData, String> crud_col_fullname;

    @FXML
    private TableColumn<StudentData, String> crud_col_gender;

    @FXML
    private TableColumn<StudentData, String> crud_col_studentnumber;

    @FXML
    private TableColumn<?, ?> crud_col_year;

    @FXML
    private ComboBox<?> crud_course;

    @FXML
    private Button crud_deletebtn;

    @FXML
    private TextField crud_fullname;

    @FXML
    private ComboBox<?> crud_genre;

    @FXML
    private Button crud_updatebtn;

    @FXML
    private ComboBox<?> crud_year;
    
    @FXML
    private TableView<StudentData> crud_tableView;
    
    
  
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement ;
    private ResultSet result;
    
    private Alert alert;
    
    private String[] yearList= {"1st year","2nd year","3rd Year","4th Year"};
    
    private String[] courseList= {"English","Arabe","Espanish"};
    private String[] genderList= {"Male","Female"};
    
    
    public void StudentYearList()
    {
    	for(String data : yearList)
    	{
    		yList.add(data);
    	}
    }
    
    
    public ObservableList<StudentData> StudentListData()
    {
    	ObservableList<StudentData> listData= FXCollections.observableArrayList();
    	String selectData = "Select * from student_info";
    	connect = database.connect();
    	
    	StudentData sData;
    	try
    	{
    		prepare = connect .prepareStatement(selectData);
    		result = prepare.executeQuery();
    		
    		while (result.next())
    		{
    			sData=new StudentData(result.getInt("student_number"), result.getString("full_name"),
    					                     result.getString("year"),
    					                    result.getString("course"),result.getString("gender"));
    			listData.add(sData);
    		}
    	}catch(Exception e) {e.printStackTrace();}
    	return listData;
    }
    
    private ObservableList<StudentData> StudentData;
    
      public void studentShowData()
      {
    	  crud_col_studentnumber.setCellValueFactory(new PropertyValueFactory<>("student_number"));
    	  crud_col_fullname.setCellValueFactory(new PropertyValueFactory<>("fullname"));
    	  crud_col_year.setCellValueFactory(new PropertyValueFactory<>("year"));
    	  crud_col_course.setCellValueFactory(new PropertyValueFactory<>("course"));
    	  crud_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
      
    	  crud_tableView.setItems(StudentData);
     
      }
      
      public void StudentSelectData()
      {
    	  StudentData sData = crud_tableView.getSelectionModel().getSelectedItem();
    	  int num = crud_tableView.getSelectionModel().getSelectedIndex();
    	  
    	  if((num-1)<-1)
    		  return;
    	  crud_StudentNumber.setText(String.valueOf(sData.getStudent_number()));
    	  crud_fullname.setText(sData.getFull_name());
    	 
    	  }
    
    
    
			
			 public void initialize(URL url, ResourceBundle rb) { 
				 //Initialization if needed 
				 }
			 
			 
			 
}
