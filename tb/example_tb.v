`timescale 1ns/1ns
`include "../src/example.v"

module mux_tb;
    reg [7:0] d0;
    reg [7:0] d1;
    reg sel_tb;

    wire [7:0] o_tb;

    mux #(.WIDTH(8)) dorianpopa (
           .data_in0(d0),
           .data_in1(d1),
           .sel(sel_tb),
           .data_out(o_tb)
        );

    initial begin
        $dumpfile("waveform.vcd");
        $dumpvars(0,mux_tb);
        #2;
        d0 = 15;
        d1 = 5;

        sel_tb = 0;
        #10 sel_tb = 1;
        #10;

    end

endmodule