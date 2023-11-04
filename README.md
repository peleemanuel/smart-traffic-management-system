# cryptographic-core

## Installing the required tools

In order to make the project easier to work in verilog and to maximize the cross compatibility (MACOS, Linux(preffered over Windows), WSL, or Windows(I dont recommend, if you want to use it you are on your own)):

OS: WSL or any kind of Linux system or MACOS
Packages: iVerilog and GTKwave

Please follow the next guide:

[Installation guide](https://circuitfever.com/verilog-simulation-using-iverilog-and-gtkwave)

## Compiling and working with the project
### How to compile:

iverilog -o <nume_fisier>.vvp <nume_fisier>.v <restul_de_fisiere>.v

How to run:
vvp <nume_fisier>.vvp

How to view waves:
gtkwave waveform.vcd

### Test your system

As test after you install everything you can test your setup:

1. ```cd /tb```
2. ```iverilog example_tb.vvp example_tb.v```
3. ```vvp example_tb.vvp```
4. ```gtkwave waveform.vcd```
5. Once you opened the gtkwave you can expand the mux_tb module in the SST box
6. Click on dorianpopa module and you will see you signals inside the module
7. Now to visualize the signals drag them from the box under the SST into the box from the right named Signals
8. Watch your signals working :)

## Workflow

```
|-- design    # Here it will go any schema designed 
|-- src       # Here it will go any verilog code 
|-- tb        # Here we are going to make testcases for each block to make sure it works
```

For more info contact @Pele,Emanuel