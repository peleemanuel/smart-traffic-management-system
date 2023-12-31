/*
 * Generated by Digital. Don't modify this file!
 * Any changes will be lost if this file is regenerated.
 */

module DIG_Mul_unsigned #(
    parameter Bits = 1
)
(
    
      input [(Bits-1):0] a,
      input [(Bits-1):0] b,
      output [(Bits*2-1):0] mul
    
);
    assign mul = a * b;
endmodule


module Multiplier (
  input A_0,
  input A_1,
  input A_2,
  input A_3,
  input A_4,
  input A_5,
  input A_6,
  input A_7,
  input A_8,
  input A_9,
  input A_10,
  input A_11,
  input A_12,
  input A_13,
  input A_14,
  input A_15,
  input B_0,
  input B_1,
  input B_2,
  input B_3,
  input B_4,
  input B_5,
  input B_6,
  input B_7,
  input B_8,
  input B_9,
  input B_10,
  input B_11,
  input B_12,
  input B_13,
  input B_14,
  input B_15,
  output Mul_0,
  output Mul_1,
  output Mul_2,
  output Mul_3,
  output Mul_4,
  output Mul_5,
  output Mul_6,
  output Mul_7,
  output Mul_8,
  output Mul_9,
  output Mul_10,
  output Mul_11,
  output Mul_12,
  output Mul_13,
  output Mul_14,
  output Mul_15
);
  wire [15:0] s0;
  wire [15:0] s1;
  wire [31:0] s2;
  assign s0[0] = A_0;
  assign s0[1] = A_1;
  assign s0[2] = A_2;
  assign s0[3] = A_3;
  assign s0[4] = A_4;
  assign s0[5] = A_5;
  assign s0[6] = A_6;
  assign s0[7] = A_7;
  assign s0[8] = A_8;
  assign s0[9] = A_9;
  assign s0[10] = A_10;
  assign s0[11] = A_11;
  assign s0[12] = A_12;
  assign s0[13] = A_13;
  assign s0[14] = A_14;
  assign s0[15] = A_15;
  assign s1[0] = B_0;
  assign s1[1] = B_1;
  assign s1[2] = B_2;
  assign s1[3] = B_3;
  assign s1[4] = B_4;
  assign s1[5] = B_5;
  assign s1[6] = B_6;
  assign s1[7] = B_7;
  assign s1[8] = B_8;
  assign s1[9] = B_9;
  assign s1[10] = B_10;
  assign s1[11] = B_11;
  assign s1[12] = B_12;
  assign s1[13] = B_13;
  assign s1[14] = B_14;
  assign s1[15] = B_15;
  DIG_Mul_unsigned #(
    .Bits(16)
  )
  DIG_Mul_unsigned_i0 (
    .a( s0 ),
    .b( s1 ),
    .mul( s2 )
  );
  assign Mul_0 = s2[0];
  assign Mul_1 = s2[1];
  assign Mul_2 = s2[2];
  assign Mul_3 = s2[3];
  assign Mul_4 = s2[4];
  assign Mul_5 = s2[5];
  assign Mul_6 = s2[6];
  assign Mul_7 = s2[7];
  assign Mul_8 = s2[8];
  assign Mul_9 = s2[9];
  assign Mul_10 = s2[10];
  assign Mul_11 = s2[11];
  assign Mul_12 = s2[12];
  assign Mul_13 = s2[13];
  assign Mul_14 = s2[14];
  assign Mul_15 = s2[15];
endmodule
