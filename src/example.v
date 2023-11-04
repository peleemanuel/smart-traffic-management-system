/*
    Un modul simplu de selectie intre doua intrari de aceiasi dimensiune.
    E facut in model combinational. in functie de bitul de selctie va alege intrarea data_in0 sau 1 si o va scrie in data_out
*/
module mux #(parameter WIDTH = 9)
  (
   // Input data lines
   input [WIDTH-1:0] data_in0,  
   input [WIDTH-1:0] data_in1,  
   // Select line
   input sel,
   // Output data line            
   output reg [WIDTH-1:0] data_out              
  );

  always @(*)
    case(sel)
      1'b0: data_out = data_in0;
      1'b1: data_out = data_in1;
    endcase

endmodule