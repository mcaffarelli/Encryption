
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class EncryptionController{
    private  final int[] array = new int[4]; //Main array used for encryption
    private  final int[] TempArray = new int[4]; //Array Used for Swaping index's
    private  final int[] array3 = new int[4]; //Array used to hold value of main array.
    private  final String[] stringArray = new String[4]; //Array to convert string into array
    private boolean bool = false; //Way to let user know to press encode button first
    private boolean bool2 = false; //Way to let user know to press encode button only once.
    @FXML
    private Button EncodeButton;

    @FXML
    private TextField TextBox;

    @FXML
    private Button DecodeButton;

    @FXML
    private Button ClearButton;

    @FXML
    private Button ExitButton;
    

    @FXML //Clear TextBox
    void ClearButtonPressed(ActionEvent event) {
    TextBox.clear();
    }
    

    @FXML
    void DecodeButtonPressed(ActionEvent event) 
    {
        if (TextBox.getText().equals("")) //If textBox is empty then ask for number
        {
            TextBox.setText("4 Digit Number Needed!");
        }
        if (bool == false) //If false then press encode button first.
        {                  //Only want user to decode number once.
           TextBox.setText("Press Encode Button First!"); 
        }
        else
        {
       
        for(int i =0; i < 4;i++) //Copy stringArray to array w/ integer values.
            array[i]=Integer.valueOf(stringArray[i]);
        
        for(int i =0; i < 4;i++) //Copy stringArray to array3 w/ integer values.
            array3[i]=Integer.valueOf(stringArray[i]);
        
        TempArray[0]=array[0]; //Copy array to TempArray
        TempArray[1]=array[1]; //Copy array to TempArray
        TempArray[2]=array[2]; //Copy array to TempArray
        TempArray[3]=array[3]; //Copy array to TempArray
        
        array[0]=TempArray[2]; //Swap digits back to original 
        array[1]=TempArray[3]; //Swap digits back to original 
        array[2]=TempArray[0]; //Swap digits back to original 
        array[3]=TempArray[1]; //Swap digits back to original
        
        array[0]=array3[0]+7; //Use array3 to copy original values +7 and assign to array
        array[1]=array3[1]+7; //Use array3 to copy original values +7 and assign to array
        array[2]=array3[2]+7; //Use array3 to copy original values +7 and assign to array
        array[3]=array3[3]+7; //Use array3 to copy original values +7 and assign to array
        
        array[0]=array[0]-7; //Subtract 7 to get back to original user input
        array[1]=array[1]-7; //Subtract 7 to get back to original user input
        array[2]=array[2]-7; //Subtract 7 to get back to original user input
        array[3]=array[3]-7; //Subtract 7 to get back to original user input
        
        //Display array w/ orignal values before encoded button changed them
        TextBox.setText((String.valueOf(array[0]))+(String.valueOf(array[1]))+(String.valueOf(array[2]))+(String.valueOf(array[3])));
        bool = false; //Set bool back to false so user cannot hit decrypt again.
        bool2 = false;//Set bool2 back to false so user can hit encrypt again.
        }
    }

    @FXML
    void EncodeButtonPressed(ActionEvent event) {
        if (TextBox.getText().equals("")) //If textBox is empty then ask for number
        {
            TextBox.setText("4 Digit Number Needed!");
        }
        if (bool2 == true) 
            {
               TextBox.setText("Enter New number!"); 
               bool2 = false; //Let user try again
            }
        else
        {
           
       String s = TextBox.getText(); //Inititalize 'S' Variable to textbox text.
            
        if (!ValidateNumber(s)) //Check and see if user entered only 4 integers.
        { 
        TextBox.setText("Invalid Input"); 
        }
        else{
            
       
        stringArray[0]= s.substring(0,1);//Assign 1st number to index 0
        stringArray[1]= s.substring(1,2);//Assign 2nd number to index 1
        stringArray[2]= s.substring(2,3);//Assign 3rd number to index 2
        stringArray[3]= s.substring(3,4);//Assign 4th number to index 3
        
        for(int i =0; i < s.length();i++) //Copy stringArray to array w/ integer values.
            array[i]=Integer.valueOf(stringArray[i]);
         
        array[0]=array[0]+7; //Add 7 to array index 0
        array[1]=array[1]+7; //Add 7 to array index 1
        array[2]=array[2]+7; //Add 7 to array index 2
        array[3]=array[3]+7; //Add 7 to array index 3
        
        array[0]=array[0]%10; //Get remainder of index 0 and assign that value to array[0].
        array[1]=array[1]%10; //Get remainder of index 1 and assign that value to array[1].
        array[2]=array[2]%10; //Get remainder of index 2 and assign that value to array[2].
        array[3]=array[3]%10; //Get remainder of index 3 and assign that value to array[3].
        
        TempArray[0]=array[0]; //Coppy array to TempArry
        TempArray[1]=array[1]; //Coppy array to TempArry
        TempArray[2]=array[2]; //Coppy array to TempArry
        TempArray[3]=array[3]; //Coppy array to TempArry
        
        array[0]=TempArray[2]; //Swap index 0 w/ 2
        array[1]=TempArray[3]; //Swap index 1 w/ 3
        array[2]=TempArray[0]; //Swap index 2 w/ 0
        array[3]=TempArray[1]; //Swap index 3 w/ 1
        
        //Display Encrypted array & Set Bool to True so user can decrypt code now.
        TextBox.setText((String.valueOf(array[0]))+(String.valueOf(array[1]))+(String.valueOf(array[2]))+(String.valueOf(array[3])));
         bool = true; //User can now hot decode button
         bool2 = true; //User cannot hit encode button again until they hit decode button.
        }
        }
       
    }

    @FXML//Exit program
    void ExitButtonPressed(ActionEvent event) {
    Platform.exit();
    }


//Method to check if user inputs only 4 digits. 
public static boolean ValidateNumber (String string){
    
    return string.matches("\\d{4}");
}
}