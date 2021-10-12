/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author elect
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;
public class Main implements Runnable, ActionListener
{
  //Class Variables
  CardLayout screenLayout; 
  JPanel screens; 
  JPanel pane1;
  JPanel pane;
  JTextField bombCounter;
  JButton [] buttons1;
  JTextField timer;
  Timer time;
  int buttonsClicked = 0;
  JTextField bombsFlagged;
  JButton smile = new JButton();
  ImageIcon sadPic = new ImageIcon("smiley3.png");
  ImageIcon smilePic = new ImageIcon("smiley1.png");
  ImageIcon normalTile = new ImageIcon("PMMinetileunpressed.png");
  ImageIcon pressedNormalTile = new ImageIcon("Mine2.9tileblank.png");
  ImageIcon [] tileNumber = new ImageIcon[9];
  ImageIcon tileNumber1 = new ImageIcon("Mine2.9tile1.png");
  ImageIcon tileNumber2 = new ImageIcon("Mine2.9tile2.png");
  ImageIcon tileNumber3 = new ImageIcon("Mine2.9tile3.png");
  ImageIcon tileNumber4 = new ImageIcon("Mine2.9tile4.png");
  ImageIcon tileNumber5 = new ImageIcon("Mine2.9tile5.png");
  ImageIcon tileNumber6 = new ImageIcon("Mine2.9tile6.png");
  ImageIcon tileNumber7 = new ImageIcon("Mine2.9tile7.png");
  ImageIcon tileNumber8 = new ImageIcon("Mine2.9tile8.png");
  ImageIcon mineIcon = new ImageIcon("PMMineicon.png");
  ImageIcon mineClicked = new ImageIcon("PMMinetilebombclicked.png");
  ImageIcon flag = new ImageIcon("c6d60c39b69ef89f5ca7396fa5639112_icon.png");
  ImageIcon smileyWonGame = new ImageIcon("smiley.png");
  int [] numberOfFlags;
  JFrame frame;
  int [] bombValue;
  int [] numberValue;
  int [] sideBoxes;
  int q = 0;
  int w = 0;
  int x = 0;

  // Method to assemble our GUI
  public void run()
  {
    // Creats a JFrame that is 800 pixels by 600 pixels, and closes when you click on the X
    JFrame frame = new JFrame("Minesweeper");
    // Makes the X button close the program
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // makes the windows 800 pixel wide by 600 pixels tall
    frame.setSize(576,600);
    // shows the window
    frame.setVisible(true);

    //Creates a main panel consisting of different difficulty levels for the game
    JPanel pane = new JPanel();
    pane.setLayout(null);

    //sets the background color of jpanel to grey
    pane.setBackground(Color.GRAY);


    //Creates buttons and sets desired settings for each button
    JButton introduction = new JButton("Introduction");
    JButton intermediate = new JButton("Intermediate");
    introduction.setBounds(213,204,150,40);
    intermediate.setBounds(213,360,150,40);
    introduction.setFont(new Font("Serif", Font.PLAIN, 16));
    intermediate.setFont(new Font("Serif", Font.PLAIN, 16));

    //Creates a title and sets font and location
    JLabel difficultyLevel = new JLabel("Pick Difficulty Level");
    difficultyLevel.setBounds(113,70,350,60);
    difficultyLevel.setFont(new Font("Serif",Font.PLAIN, 30));

    //Adds all components to main panel
    pane.add(introduction);
    pane.add(intermediate);
    pane.add(difficultyLevel);

    //Adds ActionListener and sets ActionCommand for buttons
    introduction.setActionCommand("Introduction");
    intermediate.setActionCommand("Intermediate");
    introduction.addActionListener(this);
    intermediate.addActionListener(this);

    //Creates a new cardlayout and a JPanel to hold cardlayout
    screenLayout = new CardLayout();
    screens = new JPanel();
    screens.setLayout(screenLayout);

    
    //Adds main panel to cardlayout panel
    screens.add(pane, "pane");
    
    //Adds JPanel with different screens to main frame and 
    //starts by showing the title page
    frame.add(screens);
    screenLayout.show(screens, "pane");

    //Counts the amount of buttons clicked and  
    //starts timer on second click
    buttonsClicked = 0;

    //Intializes all flag locations to zero  
    //meaning no flags have been placed yet as
    //number one means theres a flag at the location
    for(int i = 0; i < q*q; i++)
    {
      numberOfFlags[i] = 0;
    }  
  }

  // method called when a button is pressed
  public void actionPerformed(ActionEvent e)
  {
    //Gets string value of button clicked 
    String command = e.getActionCommand();

    //If introduction is clicked from title page, then calls
    //all methods with game functionality sending desired parameter values
    if(command.equals("Introduction"))
    {
      q = 9;
      w = 9;
      x = 10;
      numberOfFlags = new int[q*q];
      bombs(x);
      numberAllSquares();
      images();
      screens.add(Grid1(q,w), "pane1");
      screenLayout.show(screens, "pane1");
    }

    //If intermediate is clicked from title page, then calls
    //all methods with game functionality sending desired parameter values
    if(command.equals("Intermediate"))
    {
      q = 16;
      w = 16;
      x = 40;
      numberOfFlags = new int[q*q];
      bombs(x);
      numberAllSquares();
      images();
      screens.add(Grid1(q,w), "pane1");
      screenLayout.show(screens, "pane1");
    }

    //If two buttons have been clicked in total, the time starts
    //since it means the game has been started 
    for(int i = 0; i < q*q; i++)
    {
      if(command.equals(i + ""))
      {
        buttonsClicked++;
        if(buttonsClicked < 2)
        {
          startTimer();
        }


        //Checks to see if there is a bomb, if no bomb is found at the
        //location of the button, then places a image icon with desired number
        //as numberValue array stores the values for all the numbers of each box
        boolean isThereABomb = false;
        for(int b  = 0; b < x; b++)
        {
          if(i == bombValue[b])
          {
            isThereABomb = true;
          }

        }

        if(isThereABomb == false)
        {
          if(numberValue[i] == 0)
          {
            buttons1[i].setEnabled(false);
            buttons1[i].setIcon(tileNumber[0]);
            openNearbySquares(i);
          }
          else
          {
            if(numberValue[i] == 1)
            {
              buttons1[i].setEnabled(false);
              buttons1[i].setIcon(tileNumber[1]);
              buttons1[i].setDisabledIcon(tileNumber[1]);
            }
            if(numberValue[i] == 2)
            {
              buttons1[i].setIcon(tileNumber[2]);
              buttons1[i].setEnabled(false);
              buttons1[i].setDisabledIcon(tileNumber[2]);
            }
            if(numberValue[i] == 3)
            {
              buttons1[i].setIcon(tileNumber[3]);
              buttons1[i].setEnabled(false);
              buttons1[i].setDisabledIcon(tileNumber[3]);
            }
            if(numberValue[i] == 4)
            {
              buttons1[i].setIcon(tileNumber[4]);
              buttons1[i].setEnabled(false);
              buttons1[i].setDisabledIcon(tileNumber[4]);
            }
            if(numberValue[i] == 5)
            {
              buttons1[i].setIcon(tileNumber[5]);
              buttons1[i].setEnabled(false);
              buttons1[i].setDisabledIcon(tileNumber[5]);
            }
            if(numberValue[i] == 6)
            {
              buttons1[i].setIcon(tileNumber[6]);
              buttons1[i].setEnabled(false);
              buttons1[i].setDisabledIcon(tileNumber[6]);
            }
            if(numberValue[i] == 7)
            {
              buttons1[i].setIcon(tileNumber[7]);
              buttons1[i].setEnabled(false);
              buttons1[i].setDisabledIcon(tileNumber[7]);
            }
            if(numberValue[i] == 8)
            {
              buttons1[i].setIcon(tileNumber[8]);
              buttons1[i].setEnabled(false);
              buttons1[i].setDisabledIcon(tileNumber[8]);
            }
            
          }
        }

      } 


    }

    //If smile icon is clicked, it means the game will restart so 
    //timer is stopped and run method is called again 
    if(command.equals("smile"))
    {
      if(time != null){
        time.stop();
      }
      run();
      
    }


    //If a button is clicked with a bomb, reveals all near by bombs, puts a 
    //special red bomb image at the bomb clicked, disables timer and all buttons, 
    //and waits for user to click simle face to restart game
    for(int i = 0; i < x; i++)
    {
      if(command.equals(bombValue[i] + ""))
      {
        time.stop();
        
        buttons1[bombValue[i]].setIcon(mineClicked);
        buttons1[bombValue[i]].setEnabled(false);
        buttons1[bombValue[i]].setDisabledIcon(mineClicked);
        smile.setIcon(sadPic);
        for(int b = 0; b < x; b++)
        {
          if(b != i)
          {
            buttons1[bombValue[b]].setIcon(mineIcon);
            buttons1[bombValue[b]].setEnabled(false);
            buttons1[bombValue[b]].setDisabledIcon(mineIcon);
          }
        }

        for(int c = 0; c <= (q*q-1); c++)
        {
          for(int b = 0; b < x; b++)
          {
            if(c != bombValue[b])
            {
              if(buttons1[c].isEnabled() == true)
              {
                buttons1[c].setIcon(normalTile);
                buttons1[c].setDisabledIcon(normalTile);
                buttons1[c].setEnabled(false);
              } 
            }
          }
        }

      } 
    }







  }

  // Main method to start our program
  public static void main(String[] args)
  {
    // Creates an instance of our program
    Main gui = new Main();
    // Lets the computer know to start it in the event thread
    SwingUtilities.invokeLater(gui);
  }


  //Method that takes the length of colunm and rows to make a desired
  //game grid. Sets up the main game grid with all its components. This method
  //is called at the beginning and is added to the cardlayout panel
  public JPanel Grid1(int q, int w)
  {
    //Creates a main panel to add game components
    JPanel pane1 = new JPanel();
    pane1.setLayout(null);

    //Creates desired button array depending on the amount of rows
    //and columns entered as parameters when calling this method
    buttons1 = new JButton[q*w];

    //Intializes size per button horizontally and  
    //vertically depending on value of column and row 
    int sizePerSquareHorizontal = 576/w;
    int sizePerSquareVertical = 504/q;

    //Creates a button for simley, sets location, adds ActionListener
    //and actioncommand, and adds it to main panel
    smile = new JButton(smilePic);
    smile.setBounds(258,4,60,60);
    pane1.add(smile);
    smile.setActionCommand("smile");
    smile.addActionListener(this);

    //Sets variable from which the first button is placed on the y-axis,
    //this means the first button is placed at y-axis location 68 and goes
    //down as the rows increase untill it reaches last row
    int YAXIS = 68;

    
    //Makes two for loops to set the y-axis and x-axis of the buttons,
    //adds actioncommand and actionlistener to all the buttons, sets location,
    //adds all the buttons to main pane, and lastly adds a MouseEvent event 
    //listener to allow flags to be placed on right click
    for(int a = 0; a < q; a++)
    {


      for(int b = 0; b < q; b++)
      {

        int XAXIS = b*sizePerSquareHorizontal;
        
        buttons1[(q*a)+b] = new JButton(normalTile);
        buttons1[(q*a)+b].setBounds(XAXIS,YAXIS,sizePerSquareHorizontal,sizePerSquareVertical);
        pane1.add(buttons1[(q*a)+b]);
        buttons1[(q*a)+b].setActionCommand("" + ((q*a)+b));
        buttons1[(q*a)+b].addActionListener(this);
        final int A = a;
        final int B = b;
        buttons1[(q*a)+b].addMouseListener(new MouseAdapter()
        {
          public void mousePressed(MouseEvent e)
          {
            if(SwingUtilities.isRightMouseButton(e))
            {
              mineFlagger(((q*A)+B));
            }
          }
        }
        );
      }
        YAXIS = YAXIS + sizePerSquareVertical;
    }


    //Sets alls settings for timer textfield which takes care of time passed
    timer = new JTextField("0");
    timer.setForeground(Color.RED);
    timer.setBackground(Color.BLACK);
    timer.setBounds(406,4,120,60);
    timer.setFont(new Font("Serif", Font.PLAIN, 40));
    pane1.add(timer);

    //Sets alls settings for bombs textfield which takes care of bombs placed
    bombsFlagged = new JTextField("0");
    bombsFlagged.setForeground(Color.RED);
    bombsFlagged.setBackground(Color.BLACK);
    bombsFlagged.setBounds(50,4,120,60);
    bombsFlagged.setFont(new Font("Serif", Font.PLAIN, 40));
    pane1.add(bombsFlagged);

    //sets the background color of jpanel to grey
    pane1.setBackground(Color.GRAY);

    bombsFlagged.setEditable(false);
    timer.setEditable(false);


    //Returns the panel to the method that has called it
    return pane1;
  }
  
  //A method to determine the location of all the bombs using math random, and
  //takes a integer parameter which asks for the amount of bombs that are in the game
  public void bombs(int x)
  {

    //Creates an array for the bombs
    bombValue = new int[x];
    for(int i = 0; i < (x-1); i++)
    {
      
      //Using a while loop, uses variables doubleBombChecker and anotherCheck
      //to determine if a bomb already consists at the location 
      boolean doubleBombChecker = false;
      boolean anotherCheck = true;
      while(doubleBombChecker == false || anotherCheck == false)
      {
        anotherCheck = true;
        int bombValueHolder = (int)(Math.random()*(q*q-1) + 0);
        for(int b = 0; b <= i; b++)
        {
          if(bombValueHolder != bombValue[b])
          {
            doubleBombChecker = true;
          }
          else if(x == 10 && bombValueHolder == 9 || bombValueHolder == 0)
          {
            anotherCheck = false;
          }
          else if(x == 40 && bombValueHolder == 16 || bombValueHolder == 0)
          {
            anotherCheck = false;
          }
          else
          {
            anotherCheck = false;
            doubleBombChecker = false;
          }
        }
        if(doubleBombChecker == true && anotherCheck == true)
        {
          bombValue [i] = bombValueHolder;
        }
      }
      
    }

    if(x == 10)
    {
      bombValue[9] = 9;
    }
    if(x == 10)
    {
      bombValue[8] = 0;
    }
    if(x == 40)
    {
      bombValue[39] = 16;
    }
    if(x == 40)
    {
      bombValue[38] = 0;
    }

  }








  //Method that numbers all squares using numberValue array based on the
  //bombs in the 8 squares around it, uses no parameters at it runs depending
  //on the global varaibles q and w representing the rows and column of the grid
  public void numberAllSquares()
  {
    //Creates array with n amount of numbers depending on difficulty as each different difficulties have varied grid count
    numberValue = new int[q*w];

    //Starts by intiating all integers as zero meaning a blank square
    for(int i = 0; i < q*w; i++)
    {
      numberValue [i] = 0;

    }

    //Uses two for loops for the inner boxes as they all check for the eight
    //boxes around them, the outer boxes have less boxes to check and therefore
    //require and different calcuation, 
    for(int c = 1; c < (q-1); c++)
    {
      for(int z = 1; z < (w-1); z++)
      {
        int i = c*q + z*1;

          //Checks if a bomb is at the location already by comparing
          //it with all the bomb locations, if a bomb is there, then
          //no calculations are done 
          boolean bombPlaceChecker = false;
          for(int b = 0; b < x; b++)
          {
            if(i == bombValue[b])
            {
              bombPlaceChecker = true;
            }
          }

          //Assuming the bomb checking test is passed, creates 8 different
          //integers for the eight boxes around it, compares the bomb values with 
          //these integers and if a bomb is there, adds one to numbervalue array
          //which increases as the number of bombs registered increases
          if(bombPlaceChecker == false)
          {
            int valueHolder = i-q;
            int valueHolder2 = i+q;
            int valueHolder3 = i-1;
            int valueHolder4 = i+1;
            int valueHolder5 = i-(q+1);
            int valueHolder6 = i-(q-1);
            int valueHolder7 = i+(q+1);
            int valueHolder8 = i+(q-1);

            for(int b = 0; b < x; b++)
            {
              if(valueHolder == bombValue[b])
              {
                numberValue[i] = numberValue[i] + 1; 
              }
              if(valueHolder2 == bombValue[b])
              {
                numberValue[i] = numberValue[i] + 1; 
              }
              if(valueHolder3 == bombValue[b])
              {
                numberValue[i] = numberValue[i] + 1; 
              }
              if(valueHolder4 == bombValue[b])
              {
                numberValue[i] = numberValue[i] + 1; 
              }
              if(valueHolder5 == bombValue[b])
              {
                numberValue[i] = numberValue[i] + 1; 
              }
              if(valueHolder6 == bombValue[b])
              {
                numberValue[i] = numberValue[i] + 1; 
              }
              if(valueHolder7 == bombValue[b])
              {
                numberValue[i] = numberValue[i] + 1; 
              }
              if(valueHolder8 == bombValue[b])
              {
                numberValue[i] = numberValue[i] + 1; 
              }
            }
          }
      }
    }

    //Creates same set of calculations as previous, however reduces the amount
    //of valueHolders as a corner square only has 3 squares around its grid
    if(numberValue[0] == 0)
    {
      boolean bombPlaceChecker = false;
      for(int b = 0; b < x; b++)
      {
        if(0 == bombValue[b])
        {
          bombPlaceChecker = true;
        }
      }
      if(bombPlaceChecker == false)
      {
        int ValueHolder1 = 1;
        int ValueHolder2 = q;
        int ValueHolder3 = (q+1);
        for(int b = 0; b < x; b++)
        {
          if(ValueHolder1 == bombValue[b])
          {
            numberValue[0] = numberValue[0] + 1;
          }
          if(ValueHolder2 == bombValue[b])
          {
            numberValue[0] = numberValue[0] + 1;
          }
          if(ValueHolder3 == bombValue[b])
          {
            numberValue[0] = numberValue[0] + 1;
          }
        }
      }
    }

    //Creates same set of calculations as previous, however reduces the amount
    //of valueHolders as a corner square only has 3 squares around its grid
    if(numberValue[(q-1)] == 0)
    {
      boolean bombPlaceChecker = false;
      for(int b = 0; b < x; b++)
      {
        if((q-1) == bombValue[b])
        {
          bombPlaceChecker = true;
        }
      }
      if(bombPlaceChecker == false)
      {
        int ValueHolder1 = (q*q-2);
        int ValueHolder2 = (q*q-1);
        int ValueHolder3 = (q-2);
        for(int b = 0; b < x; b++)
        {
          if(ValueHolder1 == bombValue[b])
          {
            numberValue[q-1] = numberValue[q-1] + 1;
          }
          if(ValueHolder2 == bombValue[b])
          {
            numberValue[q-1] = numberValue[q-1] + 1;
          }
          if(ValueHolder3 == bombValue[b])
          {
            numberValue[q-1] = numberValue[q-1] + 1;
          }
        }
      }
    }

    //Creates same set of calculations as previous, however reduces the amount
    //of valueHolders as a corner square only has 3 squares around its grid
    if(numberValue[(q*q-q)] == 0)
    {
      boolean bombPlaceChecker = false;
      for(int b = 0; b < x; b++)
      {
        if((q*q-q) == bombValue[b])
        {
          bombPlaceChecker = true;
        }
      }
      if(bombPlaceChecker == false)
      {
        int ValueHolder1 = (q*q-q-q+1);
        int ValueHolder2 = (q*q-q-q);
        int ValueHolder3 = (q*q-q+1);
        for(int b = 0; b < x; b++)
        {
          if(ValueHolder1 == bombValue[b])
          {
            numberValue[q*q-q] = numberValue[q*q-q] + 1;
          }
          if(ValueHolder2 == bombValue[b])
          {
            numberValue[q*q-q] = numberValue[q*q-q] + 1;
          }
          if(ValueHolder3 == bombValue[b])
          {
            numberValue[q*q-q] = numberValue[q*q-q] + 1;
          }
        } 
      }
    }
    
    //Creates same set of calculations as previous, however reduces the amount
    //of valueHolders as a corner square only has 3 squares around its grid
    if(numberValue[q*q-1] == 0)
    {
      boolean bombPlaceChecker = false;
      for(int b = 0; b < x; b++)
      {
        if((q*q-1) == bombValue[b])
        {
          bombPlaceChecker = true;
        }
      }
      if(bombPlaceChecker == false)
      {
        int ValueHolder1 = (q*q-2);
        int ValueHolder2 = (q*q-q-1);
        int ValueHolder3 = (q*q-q-2);
        for(int b = 0; b < x; b++)
        {
          if(ValueHolder1 == bombValue[b])
          {
            numberValue[q*q-1] = numberValue[q*q-1] + 1;
          }
          if(ValueHolder2 == bombValue[b])
          {
            numberValue[q*q-1] = numberValue[q*q-1] + 1;
          }
          if(ValueHolder3 == bombValue[b])
          {
            numberValue[q*q-1] = numberValue[q*q-1] + 1;
          }
        }  
      }
    }

    //Creates an array to take into consideration the top row middle boxes, these consist
    // of 5 boxes around its grid, otherwise all the calculations are the same
    for(int i = 1; i < (q-1); i++)
    {
      boolean bombPlaceChecker = false;
      for(int b = 0; b < x; b++)
      {
        if(i == bombValue[b])
        {
          bombPlaceChecker = true;
        }
      }

      if(bombPlaceChecker == false)
      {
        int valueHolder = i - 1;
        int valueHolder2 = i + 1;
        int valueHolder3 = i + (q+1);
        int valueHolder4 = i + (q-1);
        int valueHolder5 = i + q;

        for(int b = 0; b < x; b++)
        {
          if(valueHolder == bombValue[b])
          {
            numberValue[i] = numberValue[i] + 1;
          }
          if(valueHolder2 == bombValue[b])
          {
            numberValue[i] = numberValue[i] + 1;
          }
          if(valueHolder3 == bombValue[b])
          {
            numberValue[i] = numberValue[i] + 1;
          }
          if(valueHolder4 == bombValue[b])
          {
            numberValue[i] = numberValue[i] + 1;
          }
          if(valueHolder5 == bombValue[b])
          {
            numberValue[i] = numberValue[i] + 1;
          }
        }
      }  
    }

    //Creates an array to take into consideration the top row middle boxes, these consist
    // of 5 boxes around its grid, otherwise all the calculations are the same
    for(int i = 1; i < (q-1); i++)
    {
      boolean bombPlaceChecker = false;
      for(int b = 0; b < x; b++)
      {
        if((q*q-q)+i == bombValue[b])
        {
          bombPlaceChecker = true;
        }
      }

      if(bombPlaceChecker == false)
      {
        int valueHolder = (q*q-q) + i - 1;
        int valueHolder2 = (q*q-q) + i + 1;
        int valueHolder3 = (q*q-q) + i - (q+1);
        int valueHolder4 = (q*q-q) + i - (q-1);
        int valueHolder5 = (q*q-q) + i - q;

        for(int b = 0; b < x; b++)
        {
          if(valueHolder == bombValue[b])
          {
            numberValue[(q*q-q)+i] = numberValue[(q*q-q)+i] + 1;
          }
          if(valueHolder2 == bombValue[b])
          {
            numberValue[(q*q-q)+i] = numberValue[(q*q-q)+i] + 1;
          }
          if(valueHolder3 == bombValue[b])
          {
            numberValue[(q*q-q)+i] = numberValue[(q*q-q)+i] + 1;
          }
          if(valueHolder4 == bombValue[b])
          {
            numberValue[(q*q-q)+i] = numberValue[(q*q-q)+i] + 1;
          }
          if(valueHolder5 == bombValue[b])
          {
            numberValue[(q*q-q)+i] = numberValue[(q*q-q)+i] + 1;
          }
        }
      }  
    }    

    //Creates an array to take into consideration the top row middle boxes, these consist
    // of 5 boxes around its grid, otherwise all the calculations are the same
    for(int d = 1; d < (q-1); d++)
    {
      int i = d*q;
      boolean bombPlaceChecker = false;
      for(int b = 0; b < x; b++)
      {
        if(i == bombValue[b])
        {
          bombPlaceChecker = true;
        }
      }

      if(bombPlaceChecker == false)
      {
        int valueHolder = i - q;
        int valueHolder2 = i + q;
        int valueHolder3 = i + 1;
        int valueHolder4 = i - (q-1);
        int valueHolder5 = i + (q+1);

        for(int b = 0; b < x; b++)
        {
          if(valueHolder == bombValue[b])
          {
            numberValue[i] = numberValue[i] + 1;
          }
          if(valueHolder2 == bombValue[b])
          {
            numberValue[i] = numberValue[i] + 1;
          }
          if(valueHolder3 == bombValue[b])
          {
            numberValue[i] = numberValue[i] + 1;
          }
          if(valueHolder4 == bombValue[b])
          {
            numberValue[i] = numberValue[i] + 1;
          }
          if(valueHolder5 == bombValue[b])
          {
            numberValue[i] = numberValue[i] + 1;
          }
        }
      }  
    }    

    //Creates an array to take into consideration the top row middle boxes, these consist
    // of 5 boxes around its grid, otherwise all the calculations are the same
    for(int e = 1; e < (q-1); e++)
    {
      int i = e*q+(q-1);
      boolean bombPlaceChecker = false;
      for(int b = 0; b < x; b++)
      {
        if(i == bombValue[b])
        {
          bombPlaceChecker = true;
        }
      }

      if(bombPlaceChecker == false)
      {
        int valueHolder = i - q;
        int valueHolder2 = i + q;
        int valueHolder3 = i - 1;
        int valueHolder4 = i - (q+1);
        int valueHolder5 = i + (q-1);

        for(int b = 0; b < x; b++)
        {
          if(valueHolder == bombValue[b])
          {
            numberValue[i] = numberValue[i] + 1;
          }
          if(valueHolder2 == bombValue[b])
          {
            numberValue[i] = numberValue[i] + 1;
          }
          if(valueHolder3 == bombValue[b])
          {
            numberValue[i] = numberValue[i] + 1;
          }
          if(valueHolder4 == bombValue[b])
          {
            numberValue[i] = numberValue[i] + 1;
          }
          if(valueHolder5 == bombValue[b])
          {
            numberValue[i] = numberValue[i] + 1;
          }
        }
      }   
    }



  }


  //Creates a method to open near by squares when a button is clicked,
  //if any other boxes around it are also open, it sends this new button back 
  //to this method untill all nearby open buttons have been revealed. Essentially
  //creates multiple instances of this method with each new open button
  public void openNearbySquares(int opener)
  {

    //Four checks to check if the locations nearby have any bombs.
    //Compares with all values of bombs using for loop
    boolean bombCheckerUp = false;
    for(int i = 0; i < x; i++)
    {
      if(opener-q == bombValue[i])
      {
        bombCheckerUp = true;
      }
    }
    boolean bombCheckerRight = false;
    for(int i = 0; i < x; i++)
    {
      if(opener+1 == bombValue[i])
      {
        bombCheckerRight = true;
      }
    }
    boolean bombCheckerDown = false;
    for(int i = 0; i < x; i++)
    {
      if(opener+q == bombValue[i])
      {
        bombCheckerDown = true;
      }
    }
    boolean bombCheckerLeft = false;
    for(int i = 0; i < x; i++)
    {
      if(opener-1 == bombValue[i])
      {
        bombCheckerLeft = true;
      }
    }



    //Stores variable for side buttons so when nearby buttons are opened,
    //a different calculation is done for sidebuttons to stop out of bounds 
    //error and only allow buttons to effect within the grid. This means the last button
    //from row 1 cannot open the first button from second row as this is not 
    //a game mechanic and needs to be accounted for

    sideBoxes = new int[q*2];
    boolean sideBox = false;
    for(int i = 0; i < q; i++)
    {
      sideBoxes [i] =  i*q;
      sideBoxes [i + q] = i*q + (q-1);
    }

    for(int i = 0; i < (q*2); i++)
    {
      if(opener == sideBoxes[i])
      {
        sideBox = true;
      }    
    }  

    if(sideBox == false)
    {
      if(bombCheckerUp == false)
      {
        if((opener-q >= 0) && (opener-q <= (q*q-1)))
        {
          if(buttons1[opener-q].isEnabled() == true)
          {
            if(numberValue[opener-q] == 0)
            {
              buttons1[opener-q].setEnabled(false);
              buttons1[opener-q].setIcon(tileNumber[0]);
              openNearbySquares(opener-q);
                
            }
            else
            {
            for(int i = 1; i < q; i++)
            {
              if(numberValue[opener-q] == i)
              {
                buttons1[opener-q].setEnabled(false);
                buttons1[opener-q].setIcon(tileNumber[i]);
                buttons1[opener-q].setDisabledIcon(tileNumber[i]);
                
              } 
              
            }
            }
          }  
        }
        
      }



      //Same calcualtion as above, however goes to the button that is directly below
      if(bombCheckerDown == false)
      {
        if((opener+q >= 0) && (opener+q <= (q*q-1)))
        {
          if(buttons1[opener+q].isEnabled() == true)
          {
            if(numberValue[opener+q] == 0)
            {
              buttons1[opener+q].setEnabled(false);
              buttons1[opener+q].setIcon(tileNumber[0]);
              openNearbySquares(opener+q);  
                
            }
            else
            {
            for(int i = 1; i < q; i++)
            {
              if(numberValue[opener+q] == i)
              {
                buttons1[opener+q].setEnabled(false);
                buttons1[opener+q].setIcon(tileNumber[i]);
                buttons1[opener+q].setDisabledIcon(tileNumber[i]);
              } 
            }
            }
          } 
        }
           
      }


      //Same calcualtion as above, however goes to the button on the right
      if(bombCheckerRight == false)
      {
        if((opener+1 >= 0) && (opener+1 <= (q*q-1)))
        {
          if(buttons1[opener+1].isEnabled() == true)
          {
            if(numberValue[opener+1] == 0)
            {
              buttons1[opener+1].setEnabled(false);
              buttons1[opener+1].setIcon(tileNumber[0]);
              openNearbySquares(opener+1);
            }
            else
            {
            for(int i = 1; i < q; i++)
            {
              if(numberValue[opener+1] == i)
              {
                buttons1[opener+1].setEnabled(false);
                buttons1[opener+1].setIcon(tileNumber[i]);
                buttons1[opener+1].setDisabledIcon(tileNumber[i]);
              } 
            }
            }
           
          } 
        }
        
      }



      //Same calcualtion as above, however goes to the button on the left
      if(bombCheckerLeft == false)
      {       
        if((opener-1 >= 0) && (opener-1 <= (q*q-1)))
        {
          if(buttons1[opener-1].isEnabled() == true)
          { 
            if(numberValue[opener-1] == 0)
            {
              buttons1[opener-1].setEnabled(false);
              buttons1[opener-1].setIcon(tileNumber[0]);
        
              openNearbySquares(opener-1);
            }
            else
            {
            for(int i = 1; i < q; i++)
            {
              if(numberValue[opener-1] == i)
              {
                buttons1[opener-1].setEnabled(false);
                buttons1[opener-1].setIcon(tileNumber[i]);
                buttons1[opener-1].setDisabledIcon(tileNumber[i]);
              } 
            }
            }
          }  
        }       
      }
    }  
    
    //Checks to see if this is a sidebutton, if the parameter send
    //is a sidebutton, then these set of calculations followed. Otherwise all calculations are
    //same as above method, takes in consideration how last few buttons in row 1
    //cannot open row 2 starting buttons, index out of bounds exception. All 
    //the same calculations with different requirements are calculated untill line 1300
    boolean sideBox1 = false;

    for(int i = 1; i < (q-1); i++)
    {
      if(opener == sideBoxes[i])
      {
        sideBox1 = true;
      }
      if(opener == sideBoxes[q+i]);
      {
        sideBox1 = true;
      }
    }
    
    if(sideBox1 == true)
    {
      boolean sideBoxRight = false;
      for(int i = 1; i < (q-1); i++)
      {
        if(opener == i*q+(q-1))
        {
          sideBoxRight = true;
        }
      }

      if(sideBoxRight == true)
      {
        if(bombCheckerLeft == false)
        {
          if((opener-1 >= 0) && (opener-1 <= (q*q-1)))
          {

            if(buttons1[opener-1].isEnabled() == true)
            { 
              if(numberValue[opener-1] == 0)
              {
                buttons1[opener-1].setEnabled(false);
                buttons1[opener-1].setIcon(tileNumber[0]);
                openNearbySquares(opener-1);
              }
              else
              {
              for(int i = 1; i < q; i++)
              {
                if(numberValue[opener-1] == i)
                {
                  buttons1[opener-1].setEnabled(false);
                  buttons1[opener-1].setIcon(tileNumber[i]);
                  buttons1[opener-1].setDisabledIcon(tileNumber[i]);
                } 
              }
              }
            }  
          }
        }

        if(bombCheckerDown == false)
        {
          if((opener+q >= 0) && (opener+q <= (q*q-1)))
          {
            if(buttons1[opener+q].isEnabled() == true)
            { 
              if(numberValue[opener+q] == 0)
              {
                buttons1[opener+q].setEnabled(false);
                buttons1[opener+q].setIcon(tileNumber[0]);
                openNearbySquares(opener+q);
              }
              else
              {
              for(int i = 1; i < q; i++)
              {
                if(numberValue[opener+q] == i)
                {
                  buttons1[opener+q].setEnabled(false);
                  buttons1[opener+q].setIcon(tileNumber[i]);
                  buttons1[opener+q].setDisabledIcon(tileNumber[i]);
                } 
              } 
              }
            }  
          }
        }

        if(bombCheckerUp == false)
        {  
          if((opener-q >= 0) && (opener-q <= (q*q-1)))
          {
            if(buttons1[opener-q].isEnabled() == true)
            { 
              if(numberValue[opener-q] == 0)
              {
                buttons1[opener-q].setEnabled(false);
                buttons1[opener-q].setIcon(tileNumber[0]);
                openNearbySquares(opener-q);
              }
              else
              {
              for(int i = 1; i < q; i++)
              {
                if(numberValue[opener-q] == i)
                {
                  buttons1[opener-q].setEnabled(false);
                  buttons1[opener-q].setIcon(tileNumber[i]);
                  buttons1[opener-q].setDisabledIcon(tileNumber[i]);
                } 
              } 
              }
            }  
          } 
        }        
      }

      if(sideBoxRight == false)
      {
        if(bombCheckerRight == false)
        {
          if((opener+1 >= 0) && (opener+1 <= (q*q-1)))
          {
            if(buttons1[opener+1].isEnabled() == true)
            { 
              if(numberValue[opener+1] == 0)
              {
                buttons1[opener+1].setEnabled(false);
                buttons1[opener+1].setIcon(tileNumber[0]);
                openNearbySquares(opener+1);
              }
              else
              {
              for(int i = 1; i < q; i++)
              {
                if(numberValue[opener+1] == i)
                {
                  buttons1[opener+1].setEnabled(false);
                  buttons1[opener+1].setIcon(tileNumber[i]);
                  buttons1[opener+1].setDisabledIcon(tileNumber[i]);
                } 
              } 
              }
            }  
          }
        }

        if(bombCheckerDown == false)
        {
          if((opener+q >= 0) && (opener+q <= (q*q-1)))
          {
            if(buttons1[opener+q].isEnabled() == true)
            { 
              if(numberValue[opener+q] == 0)
              {
                buttons1[opener+q].setEnabled(false);
                buttons1[opener+q].setIcon(tileNumber[0]);
                openNearbySquares(opener+q);
              }
              else
              {
              for(int i = 1; i < q; i++)
              {
                if(numberValue[opener+q] == i)
                {
                  buttons1[opener+q].setEnabled(false);
                  buttons1[opener+q].setIcon(tileNumber[i]);
                  buttons1[opener+q].setDisabledIcon(tileNumber[i]);
                } 
              } 
              }
            }  
          }
        }

        if(bombCheckerUp == false)
        {  
          if((opener-q >= 0) && (opener-q <= (q*q-1)))
          {
            if(buttons1[opener-q].isEnabled() == true)
            { 
              if(numberValue[opener-q] == 0)
              {
                buttons1[opener-q].setEnabled(false);
                buttons1[opener-q].setIcon(tileNumber[0]);
                openNearbySquares(opener-q);
              }
              else
              {
              for(int i = 1; i < q; i++)
              {
                if(numberValue[opener-q] == i)
                {
                  buttons1[opener-q].setEnabled(false);
                  buttons1[opener-q].setIcon(tileNumber[i]);
                  buttons1[opener-q].setDisabledIcon(tileNumber[i]);
                } 
              } 
              }
            }  
          } 
        }        

      }

    }



    //Last set of calculations for corner boxes as they have different grid around them,
    //does same calculation for all four corners till line 1508
    if(opener == 0)
    {
      if(bombCheckerRight == false)
      {
        if(buttons1[1].isEnabled() == true)
        { 
          if(numberValue[1] == 0)
          {
            buttons1[1].setEnabled(false);
            buttons1[1].setIcon(tileNumber[0]);
            openNearbySquares(1);
          }
          else
          {
          for(int i = 1; i < q; i++)
          {
            if(numberValue[1] == i)
            {
              buttons1[1].setEnabled(false);
              buttons1[1].setIcon(tileNumber[i]);
              buttons1[1].setDisabledIcon(tileNumber[i]);
            } 
          } 
          }
        }   
      }
      if(bombCheckerDown == false)
      {
        if(buttons1[q].isEnabled() == true)
        { 
          if(numberValue[q] == 0)
          {
            buttons1[q].setEnabled(false);
            buttons1[q].setIcon(tileNumber[0]);
            openNearbySquares(q);
          }
          else
          {
          for(int i = 1; i < q; i++)
          {
            if(numberValue[q] == i)
            {
              buttons1[q].setEnabled(false);
              buttons1[q].setIcon(tileNumber[i]);
              buttons1[q].setDisabledIcon(tileNumber[i]);
            } 
          } 
          }
        }
      }
    }
    if(opener == (q-1))
    {
      if(bombCheckerLeft == false)
      {
        if(buttons1[opener-1].isEnabled() == true)
        { 
          if(numberValue[opener-1] == 0)
          {
            buttons1[opener-1].setEnabled(false);
            buttons1[opener-1].setIcon(tileNumber[0]);
            openNearbySquares(opener-1);
          }
          else
          {
          for(int i = 1; i < q; i++)
          {
            if(numberValue[opener-1] == i)
            {
              buttons1[opener-1].setEnabled(false);
              buttons1[opener-1].setIcon(tileNumber[i]);
              buttons1[opener-1].setDisabledIcon(tileNumber[i]);
            } 
          } 
          }
        }
      }
      if(bombCheckerDown == false)
      {
        if(buttons1[opener+q].isEnabled() == true)
        { 
          if(numberValue[opener+q] == 0)
          {
            buttons1[opener+q].setEnabled(false);
            buttons1[opener+q].setIcon(tileNumber[0]);
            openNearbySquares(opener+q);
          }
          else
          {
          for(int i = 1; i < q; i++)
          {
            if(numberValue[opener+q] == i)
            {
              buttons1[opener+q].setEnabled(false);
              buttons1[opener+q].setIcon(tileNumber[i]);
              buttons1[opener+q].setDisabledIcon(tileNumber[i]);
            } 
          } 
          }
        }
      }
    }
    if(opener == (q*q-q))
    {
      if(bombCheckerUp == false)
      {
        if(buttons1[opener-q].isEnabled() == true)
        { 
          if(numberValue[opener-q] == 0)
          {
            buttons1[opener-q].setEnabled(false);
            buttons1[opener-q].setIcon(tileNumber[0]);
            openNearbySquares(opener-q);
          }
          else
          {
          for(int i = 1; i < q; i++)
          {
            if(numberValue[opener-q] == i)
            {
              buttons1[opener-q].setEnabled(false);
              buttons1[opener-q].setIcon(tileNumber[i]);
              buttons1[opener-q].setDisabledIcon(tileNumber[i]);
            } 
          } 
          }
        }
      }
      if(bombCheckerRight == false)
      {
        if(buttons1[opener+1].isEnabled() == true)
        {
          if(numberValue[opener+1] == 0)
          {
            buttons1[opener+1].setEnabled(false);
            buttons1[opener+1].setIcon(tileNumber[0]);
            openNearbySquares(opener+1);
          } 
          else
          {
          for(int i = 1; i < q; i++)
          {
            if(numberValue[opener+1] == i)
            {
              buttons1[opener+1].setEnabled(false);
              buttons1[opener+1].setIcon(tileNumber[i]);
              buttons1[opener+1].setDisabledIcon(tileNumber[i]);
            } 
          } 
          }
        }
      }
    }
    if(opener == (q*q-1))
    {
      if(bombCheckerLeft == false)
      {
        if(buttons1[opener-1].isEnabled() == true)
        { 
          if(numberValue[opener-1] == 0)
          {
            buttons1[opener-1].setEnabled(false);
            buttons1[opener-1].setIcon(tileNumber[0]);
            openNearbySquares(opener-1);
          }
          else
          {
          for(int i = 1; i < q; i++)
          {
            if(numberValue[opener-1] == i)
            {
              buttons1[opener-1].setEnabled(false);
              buttons1[opener-1].setIcon(tileNumber[i]);
              buttons1[opener-1].setDisabledIcon(tileNumber[i]);
            } 
          } 
          }
        }
      }
      if(bombCheckerUp == false)
      {
        if(buttons1[opener-q].isEnabled() == true)
        { 
          if(numberValue[opener-q] == 0)
          {
            buttons1[opener-q].setEnabled(false);
            buttons1[opener-q].setIcon(tileNumber[0]);
            openNearbySquares(opener-q);
          }
          else
          {
          for(int i = 1; i < q; i++)
          {
            if(numberValue[opener-q] == i)
            {
              buttons1[opener-q].setEnabled(false);
              buttons1[opener-q].setIcon(tileNumber[i]);
              buttons1[opener-q].setDisabledIcon(tileNumber[i]);
            } 
          } 
          }
        }
      }
    }


    //Does calculations for top row, last row, first column, and last column
    //as they have different set of grid around them, therefor different 
    // requirements until line 1758
    for(int i = 1; i < (q-1); i++)
    {
      for(int c = 1; c < (q-1); c++)
      {
        int e = q*i + c;
        if(opener == e)
        {
          int [] boxes = new int[x];
          boxes[0] = opener-1;
          boxes[1] = opener+1;
          boxes[2] = opener+q;
          boxes[3] = opener-q;
          boxes[4] = opener+(q+1);
          boxes[5] = opener-(q+1);
          boxes[6] = opener+(q-1);
          boxes[7] = opener-(q-1);

          for(int z = 0; z < (q-1); z++)
          {
            for(int y = 1; y < q; y++ )
            {
              if(numberValue[boxes[z]] == y)
              {
                buttons1[boxes[z]].setIcon(tileNumber[y]);
                buttons1[boxes[z]].setDisabledIcon(tileNumber[y]);
                buttons1[boxes[z]].setEnabled(false);

              }
               
            }

          }


        }

      }
    }


    for(int i = 1; i < (q-1); i++)
    {
      int a = i*q;
      int b = i*q+(q-1);
      int c = i;
      int d = i+(q*q-q);

      if(opener == a)
      {
        int [] boxes = new int[5];
          boxes[0] = opener-(q-1);
          boxes[1] = opener+1;
          boxes[2] = opener+q;
          boxes[3] = opener-q;
          boxes[4] = opener+(q+1);


          for(int z = 0; z < 5; z++)
          {
            for(int y = 1; y < q; y++ )
            {
              if(numberValue[boxes[z]] == y)
              {
                buttons1[boxes[z]].setIcon(tileNumber[y]);
                buttons1[boxes[z]].setDisabledIcon(tileNumber[y]);
                buttons1[boxes[z]].setEnabled(false);

              }
               
            }

          }
      }
      if(opener == b)
      {
        int [] boxes = new int[5];
          boxes[0] = opener+(q-1);
          boxes[1] = opener-1;
          boxes[2] = opener+q;
          boxes[3] = opener-q;
          boxes[4] = opener-(q+1);


          for(int z = 0; z < 5; z++)
          {
            for(int y = 1; y < q; y++ )
            {
              if(numberValue[boxes[z]] == y)
              {
                buttons1[boxes[z]].setIcon(tileNumber[y]);
                buttons1[boxes[z]].setDisabledIcon(tileNumber[y]);
                buttons1[boxes[z]].setEnabled(false);

              }
               
            }

          }
      }
      if(opener == c)
      {
        int [] boxes = new int[5];
          boxes[0] = opener+1;
          boxes[1] = opener-1;
          boxes[2] = opener+q;
          boxes[3] = opener+(q-1);
          boxes[4] = opener+(q+1);


          for(int z = 0; z < 5; z++)
          {
            for(int y = 1; y < q; y++ )
            {
              if(numberValue[boxes[z]] == y)
              {
                buttons1[boxes[z]].setIcon(tileNumber[y]);
                buttons1[boxes[z]].setDisabledIcon(tileNumber[y]);
                buttons1[boxes[z]].setEnabled(false);

              }
               
            }

          }
      }
      if(opener == d)
      {
        int [] boxes = new int[5];
          boxes[0] = opener+1;
          boxes[1] = opener-1;
          boxes[2] = opener-q;
          boxes[3] = opener-(q-1);
          boxes[4] = opener-(q+1);


          for(int z = 0; z < 5; z++)
          {
            for(int y = 1; y < q; y++ )
            {
              if(numberValue[boxes[z]] == y)
              {
                buttons1[boxes[z]].setIcon(tileNumber[y]);
                buttons1[boxes[z]].setDisabledIcon(tileNumber[y]);
                buttons1[boxes[z]].setEnabled(false);

              }
               
            }

          }
      }   

    }

    if(opener == 0)
    {
      int boxes [] = new int[3];
      boxes [0] = 1;
      boxes [1] = q;
      boxes [2] = (q+1);

      for(int z = 0; z < 3; z++)
      {
        for(int y = 1; y < q; y++ )
        {
          if(numberValue[boxes[z]] == y)
          {
            buttons1[boxes[z]].setIcon(tileNumber[y]);
            buttons1[boxes[z]].setDisabledIcon(tileNumber[y]);
            buttons1[boxes[z]].setEnabled(false);

          }
            
        }

      }


    }
    if(opener == (q-1))
    {
      int boxes [] = new int[3];
      boxes [0] = (q-2);
      boxes [1] = (q*2-2);
      boxes [2] = (q*2-1);

      for(int z = 0; z < 3; z++)
      {
        for(int y = 1; y < 9; y++ )
        {
          if(numberValue[boxes[z]] == y)
          {
            buttons1[boxes[z]].setIcon(tileNumber[y]);
            buttons1[boxes[z]].setDisabledIcon(tileNumber[y]);
            buttons1[boxes[z]].setEnabled(false);

          }
            
        }

      }

    }
    if(opener == (q*q-q))
    {
      int boxes [] = new int[3];
      boxes [0] = (q*q-q+1);
      boxes [1] = (q*q-q-q);
      boxes [2] = (q*q-q-q+1);

      for(int z = 0; z < 3; z++)
      {
        for(int y = 1; y < 9; y++ )
        {
          if(numberValue[boxes[z]] == y)
          {
            buttons1[boxes[z]].setIcon(tileNumber[y]);
            buttons1[boxes[z]].setDisabledIcon(tileNumber[y]);
            buttons1[boxes[z]].setEnabled(false);

          }  
        }
      }
    }
    if(opener == (q*q-1))
    {
      int boxes [] = new int[3];
      boxes [0] = (q*q-2);
      boxes [1] = (q*q-q-1);
      boxes [2] = (q*q-q-2);

      for(int z = 0; z < 3; z++)
      {
        for(int y = 1; y < 9; y++ )
        {
          if(numberValue[boxes[z]] == y)
          {
            buttons1[boxes[z]].setIcon(tileNumber[y]);
            buttons1[boxes[z]].setDisabledIcon(tileNumber[y]);
            buttons1[boxes[z]].setEnabled(false);
          } 
        }
      }
    }
  }


  //Creates a method to flag mines, with parameter int which tells
  //the box on the grid that was clicked (index of button), this is called
  //when right mouse is clicked using MouseEvent listener 
  public void mineFlagger(int number)
  {

    //Intializes variable counter, this represents the amount of boxes that
    //have been flagged, only a maximum of n boxes can be flagged depending
    //on the number of bombs chosen in this game by the user
    int counter = 0;

    //Uses a for loop to intialize a flag as 1 means a flag is placed there, 
    //processes only if the button is enabled, that means it has not been clicked on yet,
    //also changes the bombs flagged JTextField 
    for(int i = 0; i < q*q; i++ )
    {
      if(buttons1[i].isEnabled() == false)
      {
        if(numberOfFlags[i] == 1)
        {
          numberOfFlags[i] = 0;
          bombsFlagged.setText(String.valueOf(Integer.parseInt(bombsFlagged.getText())-1));
        }  
      }
    }

    //Counts the amount of elements in array numberOfFlags with int value of one,
    //meaning bomb is there and increases the value of counter by one each time
    for(int i = 0; i < q*q; i++)
    {
      if(numberOfFlags[i] == 1)
      {
        counter++;
      }

    }

    //If there is no flag at the location, and the total flagged boxes is less
    //then the maximum number of bombs in the game, then set that index to one
    //and increase the value of bombs flagged JTextField by one too and 
    //set a flagged image icon at that button index
    if(numberOfFlags[number] == 0)
    {
      if(counter < x)
      {
        buttons1[number].setIcon(flag);
        numberOfFlags[number] = 1;
        bombsFlagged.setText(String.valueOf(Integer.parseInt(bombsFlagged.getText())+1));
        
      }
    }  

    //If there is a flag at the location already, and this location is clicked again remove
    //the flag icon from the location, and set the element in numberOfFlags array to zero
    else if(numberOfFlags[number] == 1)
    {
      buttons1[number].setIcon(normalTile);
      numberOfFlags[number] = 0;
      bombsFlagged.setText(String.valueOf(Integer.parseInt(bombsFlagged.getText())-1));
    }

    //Intializes the variable correctLocations which means the number of flagged
    //locations that actually have a bomb under them and are correct
    int correctLocations = 0;

    //Checks the number of flagged locations and compares it with location of bombs,
    //if bomb values have a flag on top, then increases correctLocations by one
    for(int i = 0; i < q*q; i++)
    {
      for(int b = 0; b < x; b++)
      {
        if(numberOfFlags[i] == 1)
        {
          if(i == bombValue[b])
          {
            correctLocations = correctLocations + 1;
          }
        }
      }
    }

    //Lastly checks if the variable correctLocations is equal to the amount of bombs 
    //in the game, if the correctLocations variable is correct, this means the user has 
    //flagged all the bombs in the game with correct flags and the game is over. This
    //sets the simley face to happy, stops the timer, disables all the buttons, and forces
    //the user to click the simley face button to restart a new game and go to title page
    if(correctLocations == x)
    {
      smile.setIcon(smileyWonGame);
      time.stop();
      for(int i = 0; i < q*q; i++)
      {
        if(buttons1[i].isEnabled() == true)
        {
          for(int c = 0; c < q*q; c++)
          {
            if(numberOfFlags[i] == 1)
            { 
              buttons1[i].setEnabled(false);
              buttons1[i].setDisabledIcon(flag);
              buttons1[i].setIcon(flag);
            } 
            else
            {
              buttons1[i].setEnabled(false);
              buttons1[i].setDisabledIcon(normalTile);
              buttons1[i].setIcon(normalTile);
            } 
          }  
        }
      }
    }
  } 

  //Creates a method for the timer of the game
  public void startTimer() 
  {
    //Creates a seperate action listener for this method, another thread
    //so timer can be counted at regular intervals while other components are processed
    //by the other thread, allowing for two set of calculations at the same time
    //Also adds a delay so the action event in this method is called every 1 second
    //and the JTextField timer goes up by one integer converted to string each time 
    ActionListener taskPerformer = new ActionListener() 
    {
      int counter = 0;
      public void actionPerformed(ActionEvent evt) 
      {
        counter++;
        timer.setText(counter + "");
      }
    };
    time = new Timer(1000, taskPerformer);
    time.start();
  }


  //Creates a method to intialize all images and image sizes, this is called at the start
  //once the size of the game grid has been selected at the title page 
  public void images()
  {
    Image scaleImage1 = tileNumber1.getImage().getScaledInstance(576/q,504/q, Image.SCALE_DEFAULT);
    tileNumber[1] = new ImageIcon(scaleImage1);
    Image scaleImage2 = tileNumber2.getImage().getScaledInstance(576/q,504/q, Image.SCALE_DEFAULT);
    tileNumber[2] = new ImageIcon(scaleImage2);
    Image scaleImage3 = tileNumber3.getImage().getScaledInstance(576/q,504/q, Image.SCALE_DEFAULT);
    tileNumber[3] = new ImageIcon(scaleImage3);
    Image scaleImage4 = tileNumber4.getImage().getScaledInstance(576/q,504/q, Image.SCALE_DEFAULT);
    tileNumber[4] = new ImageIcon(scaleImage4);
    Image scaleImage5 = tileNumber5.getImage().getScaledInstance(576/q,504/q, Image.SCALE_DEFAULT);
    tileNumber[5] = new ImageIcon(scaleImage5);
    Image scaleImage6 = tileNumber6.getImage().getScaledInstance(576/q,504/q, Image.SCALE_DEFAULT);
    tileNumber[6] = new ImageIcon(scaleImage6);
    Image scaleImage7 = tileNumber7.getImage().getScaledInstance(576/q,504/q, Image.SCALE_DEFAULT);
    tileNumber[7] = new ImageIcon(scaleImage7);
    Image scaleImage8 = tileNumber8.getImage().getScaledInstance(576/q,504/q, Image.SCALE_DEFAULT);
    tileNumber[8] = new ImageIcon(scaleImage8);
    Image pressedNormalTile1 = pressedNormalTile.getImage().getScaledInstance(576/q,504/q, Image.SCALE_DEFAULT);
    tileNumber[0] = new ImageIcon(pressedNormalTile1);
    Image mineClicked1 = mineClicked.getImage().getScaledInstance(576/q,504/q, Image.SCALE_DEFAULT);
    mineClicked = new ImageIcon(mineClicked1);
    Image sadPic2 = sadPic.getImage().getScaledInstance(60,60, Image.SCALE_DEFAULT);
    sadPic = new ImageIcon(sadPic2);
    Image flag1 = flag.getImage().getScaledInstance(576/q,504/q, Image.SCALE_DEFAULT);
    flag = new ImageIcon(flag1);
    Image scaleImage = normalTile.getImage().getScaledInstance(576/q,504/q, Image.SCALE_DEFAULT);
    normalTile = new ImageIcon(scaleImage);
    Image smileyWonGame1 = smileyWonGame.getImage().getScaledInstance(60,60, Image.SCALE_DEFAULT);
    smileyWonGame = new ImageIcon(smileyWonGame1);
    Image smile1 = smilePic.getImage().getScaledInstance(60,60, Image.SCALE_DEFAULT);
    smilePic = new ImageIcon(smile1);
    Image scaleImage10 = mineIcon.getImage().getScaledInstance(576/q,504/q, Image.SCALE_DEFAULT);
    mineIcon = new ImageIcon(scaleImage10);

  }
}
