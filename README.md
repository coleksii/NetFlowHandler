# NetFlowHandler

## Usage
1. Project starts from MainEngine.class You should only create this class and run method startEngine(). 
You may set a Path file.csv by an argument or by a config.properties.
2. In argument you may set range Date for computing data.

## What does NetFlowHandler exactly?
1. Read NetFlow data from csv file
2. Computes the Top 10 traffic receivers, Top 10 traffic transmitters, top 3 used protocols, and top 10 used applications (ranges of top you may set from property file)
3. Write computing data in JSON format to result.file
4. Show a chart for received and transmitted bytes over time for top network transmitter and receiver.
