import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
class test{
	static JFrame frame;
	static Container c;
	public static void main(String[] args) {
    frame=new JFrame();
    c=frame.getContentPane();
    c.setLayout(null);
    c.setBackground(Color.gray);
    frame.setVisible(true);
    frame.setResizable(false);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(460,970+50);
    frame.setLocationRelativeTo(null);
    frame.setTitle("SUDOKU-SOLVER");
    move panel=new move();
    c.add(panel);
   frame.revalidate();
   frame.repaint();
	}
}
class move extends JPanel implements ItemListener,ActionListener{
int size=9;    
boolean block_status1=false,block_status2=false;
JLabel warning;
JPanel input_panel=new JPanel();
JPanel output_panel=new JPanel(){
public void paint(Graphics gx){
super.paint(gx);
Graphics2D g=(Graphics2D)gx;
g.setStroke(new BasicStroke(5));
g.setColor(Color.white);
int d=45;
if(size==9){
for(int i=0;i<8;i++){    
   g.drawLine(0,d,410,d);
   g.drawLine(d,0,d,410);
  d+=45;
  }
}
g.setColor(Color.red);
g.setStroke(new BasicStroke(10));
int c=136;
for(int i=0;i<2;i++){
g.drawLine(0,c,410,c);
g.drawLine(c,0,c,410);
c+=136;
         }
      }
};  
JButton bt;
JTextField in_arr[][];
JLabel out_arr[][];
int input_array[][],output_array[][];
JCheckBox size_3,size_9;
ButtonGroup group;
public void paint(Graphics g){
super.paint(g);    
g.setColor(Color.green);
g.setFont(new Font("Arial",Font.BOLD,25));
g.drawString("Set Size:",0,30);
g.drawString("INPUT-BOARD",120,480);
g.drawString("OUTPUT-BOARD",120,920);
g.drawLine(0,922,410,922);
g.setColor(Color.magenta);
    }
move(){
this.setBounds(15,15,410,950);
this.setBackground(Color.black);
this.setLayout(null);

warning=new JLabel("WARNING:PLEASE ENTER VALIDE DATA.",JLabel.CENTER);
warning.setForeground(Color.red);
warning.setBounds(100,922,300,30);

bt=new JButton("SOLVE");
bt.setBounds(0,922,100,30);
bt.addActionListener(this);
group=new ButtonGroup();

input_panel.setBounds(0,40,410,410);
input_panel.setBackground(Color.green);
input_panel.setLayout(new GridLayout(size,size));

output_panel.setBounds(0,490,410,410);
output_panel.setBackground(Color.orange);
output_panel.setLayout(new GridLayout(size,size));

size_3=new JCheckBox("(3*3)");
size_9=new JCheckBox("(9*9)");
size_3.setBounds(110,0,150,40);size_3.setFont(new Font("Arial",Font.BOLD,25));
size_3.setBackground(Color.black);size_3.setForeground(Color.cyan);
size_3.setHorizontalAlignment(SwingConstants.CENTER);
size_9.setBounds(260,0,150,40);size_9.setFont(new Font("Arial",Font.BOLD,25));
size_9.setBackground(Color.black);size_9.setForeground(Color.cyan);
size_9.setHorizontalAlignment(SwingConstants.CENTER);
size_9.setSelected(true);
size_3.addItemListener(this);
size_9.addItemListener(this);
group.add(size_3);
group.add(size_9);
make_input();
make_output();
this.add(input_panel);
this.add(output_panel);
this.add(size_3);
this.add(size_9);
this.add(bt);
this.add(warning);
warning.setVisible(false);
   }
public void make_input(){
input_panel.setLayout(new GridLayout(size,size));
input_panel.removeAll();
in_arr=new JTextField[size][size];

for(int i=0;i<size;i++){
   for(int j=0;j<size;j++){    
     in_arr[i][j]=new JTextField("0");
     input_panel.add(in_arr[i][j]);
     if(size==3)
     in_arr[i][j].setFont(new Font("Arial",Font.BOLD,110));
     if(size==9)
     in_arr[i][j].setFont(new Font("Arial",Font.BOLD,25));    
     in_arr[i][j].setHorizontalAlignment(SwingConstants.CENTER);
      in_arr[i][j].setBackground(Color.green);
   if(size==9){   
   if((j==0 || j==1 || j==2 || j==6 || j==7 || j==8 )  && (i==0 || i==1 || i==2 || i==6 || i==7 || i==8))
         in_arr[i][j].setBackground(Color.cyan);
    if((j==3 || j==4 || j==5) && (i==3 || i==4 || i==5 ))
          in_arr[i][j].setBackground(Color.cyan); 
             }
    if(size==3){
    if((i==j) || (i==0 && j==2) || (i==2 && j==0))
                  in_arr[i][j].setBackground(Color.cyan); 

            }         
        }                
    }
input_panel.repaint();
input_panel.revalidate();    
}  

public void make_output(){  
output_panel.setLayout(new GridLayout(size,size));
output_panel.removeAll();
out_arr=new JLabel[size][size];

for(int i=0;i<size;i++){
   for(int j=0;j<size;j++){    
     out_arr[i][j]=new JLabel("0",JLabel.CENTER);
     output_panel.add(out_arr[i][j]);
     if(size==3)
     out_arr[i][j].setFont(new Font("Arial",Font.BOLD,110));
     if(size==9)
     out_arr[i][j].setFont(new Font("Arial",Font.BOLD,25));    
     out_arr[i][j].setBackground(Color.cyan);
        }                
    }
output_panel.repaint();
output_panel.revalidate();
}

public void getdata(){
input_array=new int[size][size];
output_array=new int[size][size];    
for(int i=0;i<size;i++){
for(int j=0;j<size;j++){
try{    
input_array[i][j]=Integer.parseInt(in_arr[i][j].getText());
}catch(Exception e){block_status1=true;}
    }
  }
}
public void check(){ 
if(size==3){
for(int i=0;i<3;i++){
for(int j=0;j<3;j++){
if(input_array[i][j]<0 || input_array[i][j]>3){
block_status2=true;
break;
           }
       }
    }
}
if(size==9){
for(int i=0;i<9;i++){
for(int j=0;j<9;j++){
if(input_array[i][j]<0 || input_array[i][j]>9){
block_status2=true;
break;
           }
       }
    }
  }
}
public void itemStateChanged(ItemEvent ev){
if(size_3.isSelected())
    size=3;
if(size_9.isSelected())
    size=9;
    make_input();
    make_output();
    input_panel.repaint();
    output_panel.repaint();
    }
public void actionPerformed(ActionEvent action){
if(action.getSource()==bt){
block_status1=false;    
block_status2=false;    
getdata();
check();
if(block_status1==true || block_status2==true){
warning.setVisible(true);    
}
else{
warning.setVisible(false);        
ai obj=new ai();
if(obj.input_data(input_array)==null){
warning.setText("WARNING:No solution");
}
else{
output_array=obj.input_data(input_array);
for(int i=0;i<size;i++){
for(int j=0;j<size;j++){
out_arr[i][j].setText(""+output_array[i][j]);
         }
       }
     }
    }
   }
  }
}


class ai 
{ 
public  boolean isSafe(int[][] board,  

                             int row, int col,  

                             int num)  
{ 
    for (int d = 0; d < board.length; d++)  
    { 
        if (board[row][d] == num)  
        { 
            return false; 
        } 
    } 
    for (int r = 0; r < board.length; r++) 
    { 
        if (board[r][col] == num) 
        { 
            return false; 
        } 
    } 
    int sqrt = (int) Math.sqrt(board.length); 
    int boxRowStart = row - row % sqrt; 
    int boxColStart = col - col % sqrt; 
    for (int r = boxRowStart; 

             r < boxRowStart + sqrt; r++)  
    { 
        for (int d = boxColStart;  

                 d < boxColStart + sqrt; d++)  
        { 
            if (board[r][d] == num)  
            { 
                return false; 
            } 
        } 
    } 
   return true; 
} 

public  boolean solveSudoku(int[][] board, int n)  
{ 
    int row = -1; 
    int col = -1; 
    boolean isEmpty = true; 
    for (int i = 0; i < n; i++) 

    { 
        for (int j = 0; j < n; j++)  
        { 
            if (board[i][j] == 0)  
            { 
                row = i; 
                col = j; 
                isEmpty = false;  
                break; 
            } 
        } 
        if (!isEmpty) 
        { 
            break; 
        } 
    } 
    if (isEmpty)  

    { 
        return true; 
    } 
    for (int num = 1; num <= n; num++) 

    { 
        if (isSafe(board, row, col, num)) 
        { 
            board[row][col] = num; 
            if (solveSudoku(board, n))  
            { 
                return true; 
            }  

            else

            { 
                board[row][col] = 0; 
            } 
        } 
    } 

    return false; 
} 

public  int[][] input_data(int board[ ][ ]) 
{ 
    int N = board.length; 
    if (solveSudoku(board, N)) 
    { 
       return board;
    }  
  return null; 
  }
} 