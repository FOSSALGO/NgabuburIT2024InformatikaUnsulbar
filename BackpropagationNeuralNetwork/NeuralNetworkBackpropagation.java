package fosalgo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class NeuralNetworkBackpropagation {

    public static void main(String[] args) {
        String filename = "src/fosalgo/Invistico_Airline.csv";//sesuaikan dengan alamat local file datasetnya
        String[] header = null;
        double[][] data = null;

        //----------------------------------------------------------------------
        // BACA FILE
        //----------------------------------------------------------------------
        File file = new File(filename);
        try {
            Scanner sc = new Scanner(file);

            // Baca header
            String baris = sc.nextLine();
            header = baris.split(",");
            // System.out.println(Arrays.toString(header));    

            // Baca baris data
            ArrayList<String[]> dataString = new ArrayList<>();
            while (sc.hasNextLine()) {
                baris = sc.nextLine();
                String[] kolom = baris.split(",");
                if (kolom.length == header.length) {
                    dataString.add(kolom);
                }
            }

            // Transformasi data
            double[][] dataDouble = new double[dataString.size()][header.length];
            for (int i = 0; i < dataDouble.length; i++) {
                String[] kolom = dataString.get(i);

                // satisfaction
                String value = kolom[0];
                double satisfaction = 0;
                if (value.equalsIgnoreCase("dissatisfied")) {
                    satisfaction = 0;
                } else if (value.equalsIgnoreCase("satisfied")) {
                    satisfaction = 1;
                }
                dataDouble[i][0] = satisfaction;

                // Gender
                value = kolom[1];
                double gender = 0;
                if (value.equalsIgnoreCase("Female")) {
                    gender = 0;
                } else if (value.equalsIgnoreCase("Male")) {
                    gender = 1;
                }
                dataDouble[i][1] = gender;

                // Customer Type
                value = kolom[2];
                double customerType = 0;
                if (value.equalsIgnoreCase("disloyal Customer")) {
                    customerType = 0;
                } else if (value.equalsIgnoreCase("Loyal Customer")) {
                    customerType = 1;
                }
                dataDouble[i][2] = customerType;

                // Age
                value = kolom[3];
                double age = Double.parseDouble(value);
                dataDouble[i][3] = age;

                // Type of Travel
                value = kolom[4];
                double typeOfTravel = 0;
                if (value.equalsIgnoreCase("Personal Travel")) {
                    typeOfTravel = 0;
                } else if (value.equalsIgnoreCase("Business travel")) {
                    typeOfTravel = 1;
                }
                dataDouble[i][4] = typeOfTravel;

                // Class
                value = kolom[5];
                double flightClass = 0;
                if (value.equalsIgnoreCase("Eco Plus")) {
                    flightClass = 1;
                } else if (value.equalsIgnoreCase("Eco")) {
                    flightClass = 2;
                } else if (value.equalsIgnoreCase("Business")) {
                    flightClass = 3;
                }
                dataDouble[i][5] = flightClass;

                // Flight Distance
                value = kolom[6];
                double flightDistance = Double.parseDouble(value);
                dataDouble[i][6] = flightDistance;

                // Seat comfort
                value = kolom[7];
                double seatComfort = Double.parseDouble(value);
                dataDouble[i][7] = seatComfort;

                // Departure/Arrival time convenient
                value = kolom[8];
                double departureArrivalTimeConvenient = Double.parseDouble(value);
                dataDouble[i][8] = departureArrivalTimeConvenient;

                // Food and drink
                value = kolom[9];
                double foodAndDrink = Double.parseDouble(value);
                dataDouble[i][9] = foodAndDrink;

                // Gate location
                value = kolom[10];
                double gateLocation = Double.parseDouble(value);
                dataDouble[i][10] = gateLocation;

                // Inflight wifi service
                value = kolom[11];
                double inflightWifiService = Double.parseDouble(value);
                dataDouble[i][11] = inflightWifiService;

                // Inflight entertainment
                value = kolom[12];
                double inflightEntertainment = Double.parseDouble(value);
                dataDouble[i][12] = inflightEntertainment;

                // Online support
                value = kolom[13];
                double onlineSupport = Double.parseDouble(value);
                dataDouble[i][13] = onlineSupport;

                // Ease of Online booking
                value = kolom[14];
                double easeOfOnlineBooking = Double.parseDouble(value);
                dataDouble[i][14] = easeOfOnlineBooking;

                // On-board service
                value = kolom[15];
                double onBoardService = Double.parseDouble(value);
                dataDouble[i][15] = onBoardService;

                // Leg room service
                value = kolom[16];
                double legRoomService = Double.parseDouble(value);
                dataDouble[i][16] = legRoomService;

                // Baggage handling
                value = kolom[17];
                double baggageHandling = Double.parseDouble(value);
                dataDouble[i][17] = baggageHandling;

                // Checkin service
                value = kolom[18];
                double checkinService = Double.parseDouble(value);
                dataDouble[i][18] = checkinService;

                // Cleanliness
                value = kolom[19];
                double cleanliness = Double.parseDouble(value);
                dataDouble[i][19] = cleanliness;

                // Online boarding
                value = kolom[20];
                double onlineBoarding = Double.parseDouble(value);
                dataDouble[i][20] = onlineBoarding;

                // Departure Delay in Minutes
                value = kolom[21];
                double departureDelayInMinutes = Double.parseDouble(value);
                dataDouble[i][21] = departureDelayInMinutes;

                // Arrival Delay in Minutes
                value = kolom[22];
                double arrivalDelayInMinutes = Double.parseDouble(value);
                dataDouble[i][22] = arrivalDelayInMinutes;
            }

            // Min-Max
            double[] min = new double[header.length];
            double[] max = new double[header.length];
            for (int j = 0; j < header.length; j++) {
                min[j] = Double.MAX_VALUE;
                max[j] = Double.MIN_VALUE;
            }
            for (int i = 0; i < dataDouble.length; i++) {
                for (int j = 0; j < dataDouble[i].length; j++) {
                    double value = dataDouble[i][j];
                    if (value < min[j]) {
                        min[j] = value;
                    }
                    if (value > max[j]) {
                        max[j] = value;
                    }
                }
            }

            // Normalisasi
            data = new double[dataDouble.length][header.length];
            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < data[i].length; j++) {
                    double value = dataDouble[i][j];
                    double normalValue = (value - min[j]) / (max[j] - min[j]);
                    data[i][j] = normalValue;
                }
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        //----------------------------------------------------------------------
        // BACA FILE SELESAI
        //----------------------------------------------------------------------

        //----------------------------------------------------------------------
        // SPLIT DATA TRAINING DAN DATA TESTING
        //----------------------------------------------------------------------
        int numData = 0;
        int numDataTraining = 0;
        int numDataTesting = 0;
        double percentDataTraining = 80;
        double percentDataTesting = 20;
        double[][] dataTraining = null;
        double[][] dataTesting = null;
        if (header != null && data != null) {
            double totalPercent = percentDataTraining + percentDataTesting;
            numData = data.length;
            numDataTraining = (int) Math.floor(numData * (percentDataTraining / totalPercent));
            numDataTesting = (int) Math.floor(numData * (percentDataTesting / totalPercent));
            //System.out.println("numData         : " + numData);
            //System.out.println("numDataTraining : " + numDataTraining);
            //System.out.println("numDataTesting  : " + numDataTesting);

            // SPLIT DATA TRAINING DAN DATA TESTING
            // set data training
            dataTraining = new double[numDataTraining][];
            for (int t = 0; t < dataTraining.length; t++) {
                dataTraining[t] = data[t];
            }
            // set data testing
            dataTesting = new double[numDataTesting][];
            for (int t = 0; t < dataTesting.length; t++) {
                dataTesting[t] = data[t + dataTraining.length];
            }
        }
        //----------------------------------------------------------------------
        // SPLIT DATA TRAINING DAN DATA TESTING SELESAI
        //----------------------------------------------------------------------

        //----------------------------------------------------------------------
        // PROSES TRAINING
        //----------------------------------------------------------------------     
        int MAX_EPOCH = 100;
        int numInput = 1;
        int numHidden = 10;
        int numOutput = 1;
        double alpha = 0.01;
        double threshold = 0;
        double epsilonThreashold = 0.0000001;

        // Array Bobot atau model Network
        double[][] bobotV = null;
        double[][] bobotW = null;

        if (dataTraining != null) {
            // Persiapan Training
            numInput = dataTraining[0].length;
            double[][] V = new double[numInput][numHidden];
            double[][] W = new double[numHidden + 1][numOutput];

            // Ininisialisasi elemen-elemen array V dan W secara random
            Random r = new Random();
            // random array bobot V
            for (int i = 0; i < V.length; i++) {
                for (int j = 0; j < V[i].length; j++) {
                    V[i][j] = r.nextDouble();
                }
            }
            // random array bobot W
            for (int j = 0; j < W.length; j++) {
                for (int k = 0; k < W[j].length; k++) {
                    W[j][k] = r.nextDouble();
                }
            }

            boolean isConvergen = false;
            int epoch = 1;
            while (epoch <= MAX_EPOCH && !isConvergen) {
                int numYsamadenganT = 0;
                for (int t = 0; t < dataTraining.length; t++) {
                    // Inisialisasi Neuron Input dan Target
                    // T adalah target
                    // X adalah Neuron Input
                    numInput = dataTraining[t].length;
                    double[] T = new double[numOutput];
                    T[0] = dataTraining[t][0];
                    //geser satu index ke kiri untuk neuron input
                    double[] X = new double[numInput];
                    for (int i = 1; i < numInput; i++) {
                        X[i - 1] = dataTraining[t][i];
                    }
                    X[numInput - 1] = 1;// untuk bias

                    //----------------------------------------------------------
                    // feedforward
                    //----------------------------------------------------------
                    // Hitung Znet
                    double[] Znet = new double[numHidden];
                    for (int j = 0; j < numHidden; j++) {
                        double sum = 0;
                        for (int i = 0; i < numInput; i++) {
                            double XiVij = X[i] * V[i][j];
                            sum += XiVij;
                        }
                        Znet[j] = sum;
                    }

                    // Hitung Z
                    double[] Z = new double[numHidden + 1];
                    for (int j = 0; j < numHidden; j++) {
                        Z[j] = 1.0 / (1.0 + Math.exp(-Znet[j]));
                    }
                    Z[numHidden] = 1;// untuk bias

                    // Hitung Ynet
                    double[] Ynet = new double[numOutput];
                    for (int k = 0; k < numOutput; k++) {
                        double sum = 0;
                        for (int j = 0; j < Z.length; j++) {
                            double ZjWjk = Z[j] * W[j][k];
                            sum += ZjWjk;
                        }
                        Ynet[k] = sum;
                    }

                    // Hitung Y
                    double[] Y = new double[numOutput];
                    for (int k = 0; k < numOutput; k++) {
                        Y[k] = 1.0 / (1.0 + Math.exp(-Ynet[k]));
                    }

                    //update threshold
                    for (int k = 0; k < numOutput; k++) {
                        if (T[k] >= 1 && threshold > Y[k]) {
                            threshold = Y[k] - epsilonThreashold;
                        } else if (T[k] <= 0 && threshold < Y[k]) {
                            threshold = Y[k] + epsilonThreashold;
                        }
                    }

                    //----------------------------------------------------------
                    // backpropagation       
                    //----------------------------------------------------------                    
                    int YT = 0;                    
                    // Hitung Faktor Error di layer output
                    double[] doY = new double[numOutput];//faktor do di unit output
                    for (int k = 0; k < numOutput; k++) {
                        doY[k] = (T[k] - Y[k]) * Y[k] * (1 - Y[k]);
                        //cek YsamadenganT
                        if (Y[k] == T[k]) {
                            YT++;
                        }
                    }

                    //cek numYsamadenganT
                    if (YT == T.length) {
                        numYsamadenganT++;
                    }

                    // Hitung deltaW
                    double[][] deltaW = new double[numHidden + 1][numOutput];
                    for (int j = 0; j < deltaW.length; j++) {
                        for (int k = 0; k < deltaW[j].length; k++) {
                            deltaW[j][k] = alpha * doY[k] * Z[j];
                        }
                    }

                    // Hitung doNetY
                    double[] doYnet = new double[numHidden];
                    for (int j = 0; j < numHidden; j++) {
                        double sum = 0;
                        for (int k = 0; k < numOutput; k++) {
                            double doYkWjk = doY[k] * W[j][k];
                            sum += doYkWjk;
                        }
                        doYnet[j] = sum;
                    }

                    // Hitung doZ
                    double[] doZ = new double[numHidden];
                    for (int j = 0; j < numHidden; j++) {
                        doZ[j] = doYnet[j] * Z[j] * (1 - Z[j]);
                    }

                    // Hitung deltaV
                    double[][] deltaV = new double[numInput][numHidden];
                    for (int i = 0; i < numInput; i++) {
                        for (int j = 0; j < numHidden; j++) {
                            deltaV[i][j] = alpha * doZ[j] * X[i];
                        }
                    }

                    //----------------------------------------------------------
                    // update bobot      
                    //----------------------------------------------------------
                    // Update bobot W
                    for (int j = 0; j < W.length; j++) {
                        for (int k = 0; k < W[j].length; k++) {
                            W[j][k] = W[j][k] + deltaW[j][k];
                        }
                    }

                    // Update bobot V
                    for (int i = 0; i < V.length; i++) {
                        for (int j = 0; j < V[i].length; j++) {
                            V[i][j] = V[i][j] + deltaV[i][j];
                        }
                    }

                }//end fo for t

                // Cek Konvergensi
                if (numYsamadenganT == dataTraining.length) {
                    isConvergen = true;
                }

                //increment epoch
                epoch++;

            }//end of while(epoch <= MAX_EPOCH)

            // Set Bobot atau model Backpropagation Neural Network
            bobotV = V;
            bobotW = W;
        }
        //----------------------------------------------------------------------
        // PROSES TRAINING SELESAI
        //----------------------------------------------------------------------

        //----------------------------------------------------------------------
        // PROSES TESTING
        //----------------------------------------------------------------------  
        int nTrue = 0;
        if (dataTesting != null && bobotV != null && bobotW != null) {
            for (int t = 0; t < dataTesting.length; t++) {
                numInput = dataTesting[t].length;
                double[] T = new double[numOutput];
                T[0] = dataTesting[t][0];
                //geser satu index ke kiri untuk neuron input
                double[] X = new double[numInput];
                for (int i = 1; i < numInput; i++) {
                    X[i - 1] = dataTesting[t][i];
                }
                X[numInput - 1] = 1;// untuk bias

                //----------------------------------------------------------
                // feedforward
                //----------------------------------------------------------
                // Hitung Znet
                double[] Znet = new double[numHidden];
                for (int j = 0; j < numHidden; j++) {
                    double sum = 0;
                    for (int i = 0; i < numInput; i++) {
                        double XiVij = X[i] * bobotV[i][j];
                        sum += XiVij;
                    }
                    Znet[j] = sum;
                }

                // Hitung Z
                double[] Z = new double[numHidden + 1];
                for (int j = 0; j < numHidden; j++) {
                    Z[j] = 1.0 / (1.0 + Math.exp(-Znet[j]));
                }
                Z[numHidden] = 1;// untuk bias

                // Hitung Ynet
                double[] Ynet = new double[numOutput];
                for (int k = 0; k < numOutput; k++) {
                    double sum = 0;
                    for (int j = 0; j < Z.length; j++) {
                        double ZjWjk = Z[j] * bobotW[j][k];
                        sum += ZjWjk;
                    }
                    Ynet[k] = sum;
                }

                // Hitung Y
                double[] Y = new double[numOutput];
                for (int k = 0; k < numOutput; k++) {
                    Y[k] = 1.0 / (1.0 + Math.exp(-Ynet[k]));
                }

                double[] Ytreshold = new double[numOutput];
                //double threshold = 0.91;
                for (int k = 0; k < numOutput; k++) {
                    if (Y[k] >= threshold) {
                        Ytreshold[k] = 1;
                    } else {
                        Ytreshold[k] = 0;
                    }
                }

                //Trace Result
                System.out.print("data_testing-" + t + " Target - Prediksi ");
                for (int k = 0; k < numOutput; k++) {
                    if (k > 0) {
                        System.out.print(", ");
                    }
                    System.out.print("[" + T[k] + "-" + Ytreshold[k] + ":");
                    if (T[k] == Ytreshold[k]) {
                        System.out.print(" True ");
                        nTrue++;
                    } else {
                        System.out.print(" False ");
                    }
                    System.out.print("]");
                }
                System.out.println();

            }//end of for t

            double akurasi = ((double) nTrue / (double) numDataTesting) * 100;
            System.out.println("---------------------------------------------------");
            System.out.println("Threshold: "+threshold);
            System.out.println("AKURASI: " + String.format("%.2f", akurasi) + "%");
        }
        //----------------------------------------------------------------------
        // PROSES TESTING SELESAI
        //----------------------------------------------------------------------

    }//end of main
}
