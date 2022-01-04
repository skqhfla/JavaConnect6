package app.myapp.imageprocessing;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Convolution {
	
	public static double[][] imageToArray(BufferedImage image){
		double[][] output = new double[image.getHeight()][image.getWidth()];
		for (int y=0; y<image.getHeight(); y++) {
			for(int x=0; x<image.getWidth(); x++) {
				Color c = new Color(image.getRGB(x,y));
				output[y][x] += c.getRed();
				output[y][x] += c.getGreen();
				output[y][x] += c.getBlue();
				output[y][x] /= 3.0;
			}
		}
		return output;
	}
	
	public static double[][] getFilter(int size){
		int x = size / 2;
		double[][] output = new double[size][size];
		double sum = 0;
		
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				output[i][j] = 1.0/(1 + Math.pow(i-x, 2) + Math.pow(j-x, 2)+ 0.5);
				sum += output[i][j];
			}
		}
		
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				output[i][j] /= sum;
			}
		}
		
		return output;
	}
	
	public static double[][] convolution(double[][] imageArray, double[][] filter) {
		if(filter.length %2 == 1) {
			int w = filter.length / 2;
			double[][] output = new double[imageArray.length][imageArray[0].length];
			
			for(int y=0; y<imageArray.length; y++) {
				for(int x=0; x<imageArray[y].length; x++) {
					for(int i=0; i<filter.length; i++) {
						for(int j=0; j<filter[i].length; j++) {
							try {
								output[y][x] += imageArray[y-i+w][x-j+w] * filter[i][j];
							} catch (ArrayIndexOutOfBoundsException AIOBe) {
								
							}
						}
					}
				}
			}
			
			return output;
		}
		else return null;
	}
	
	public static double[][] arrayInColorBound(double[][] imageArray){
		for(int i=0; i<imageArray.length; i++) {
			for(int j=0; j<imageArray[i].length; j++) {
				imageArray[i][j] = Math.max(0, imageArray[i][j]);
				imageArray[i][j] = Math.min(255, imageArray[i][j]);
			}
		}
		return imageArray;
	}
	
	public static double[][] arrayColorInverse(double[][] imageArray){
		for(int i=0; i<imageArray.length; i++) {
			for(int j=0; j<imageArray[i].length; j++) {
				imageArray[i][j] = 255 - imageArray[i][j];
			}
		}
		return imageArray;
	}
	
	public static BufferedImage arrayToImage(double[][] imageArray) {
		BufferedImage output = new BufferedImage(imageArray[0].length, imageArray.length, BufferedImage.TYPE_INT_BGR);
		for(int y=0; y<imageArray.length; y++) {
			for(int x=0; x<imageArray[y].length; x++) {
				output.setRGB(x, y, new Color((int) imageArray[y][x], (int) imageArray[y][x], (int) imageArray[y][x]).getRGB());
			}
		}
		return output;
	}
	
}
