/**
 * 
 */
package model;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Robert
 */
public class RussianMatrixAdapter {

    protected static Logger logger = Logger.getLogger("AdapterLogger");

    RussianMatrix a = null;
    RussianMatrix b = null;
    int ciIndex;

    public RussianMatrixAdapter() {
	ciIndex = 0;
    }

    public void mul() {
	try {
	    if (a == null || b == null) {
		throw new NullPointerException();
	    }
	    a.mulMatrix(b);
	} catch (NullPointerException e) {
	    Logger.getGlobal().log(Level.SEVERE, "cannot mul with null matrix");
	    e.printStackTrace();
	}
    }

    public String bToColoredString() {
	StringBuilder bToString = new StringBuilder("<html><table>");

	try {
	    if (ciIndex < 0 && ciIndex > RussianMatrix.getP()) {
		throw new IllegalArgumentException();
	    }
	    if (b == null) {
		throw new NullPointerException();
	    }
	    ArrayList<ArrayList<Boolean>> matrix = b.getInfo();
	    int m = RussianMatrix.getM();
	    int p = RussianMatrix.getP();

	    int indexOfRow = 0;
	    for (ArrayList<Boolean> row : matrix) {
		bToString.append("<tr>");
		for (Boolean element : row) {
		    bToString.append("<td>");
		    if (ciIndex != p && indexOfRow / m == ciIndex) {
			bToString.append("<font color=\"#B90000\"><b>");
			bToString.append(element ? "1" : "0");
			bToString.append("</b></font>");
		    } else {
			bToString.append(element ? "1" : "0");
		    }
		    bToString.append("</td>");
		}
		bToString.append("</tr>");
		indexOfRow++;
	    }
	} catch (IllegalArgumentException e) {
	    logger.log(Level.SEVERE, "index of ci not correct");
	    e.printStackTrace();
	} catch (NullPointerException e) {
	    logger.log(Level.SEVERE, "b matrix not created");
	}
	bToString.append("</table></html>");

	return bToString.toString();
    }

    public String aToColoredString() {
	StringBuilder bToString = new StringBuilder("<html><table>");

	try {
	    if (ciIndex < 0 && ciIndex >= RussianMatrix.getP()) {
		throw new IllegalArgumentException();
	    }
	    if (b == null) {
		throw new NullPointerException();
	    }
	    ArrayList<ArrayList<Boolean>> matrix = a.getInfo();
	    int m = RussianMatrix.getM();
	    int p = RussianMatrix.getP();
	    for (ArrayList<Boolean> row : matrix) {
		bToString.append("<tr>");
		int indexOfEl = 0;
		for (Boolean element : row) {
		    bToString.append("<td>");
		    if (ciIndex != p && indexOfEl++ / m == ciIndex) {
			bToString.append("<font color=\"#B90000\"><b>");
			bToString.append(element ? "1" : "0");
			bToString.append("</b></font>");
		    } else {
			bToString.append(element ? "1" : "0");
		    }
		    bToString.append("</td>");
		}
		bToString.append("</tr>");
	    }
	} catch (IllegalArgumentException e) {
	    logger.log(Level.SEVERE, "index of ci not correct");
	    e.printStackTrace();
	} catch (NullPointerException e) {
	    logger.log(Level.SEVERE, "b matrix not created");
	}
	bToString.append("</table></html>");

	return bToString.toString();
    }

    public String bToString() {
	StringBuilder bToString = new StringBuilder("<html><table>");

	try {
	    if (b == null) {
		throw new NullPointerException();
	    }
	    ArrayList<ArrayList<Boolean>> matrix = b.getInfo();

	    for (ArrayList<Boolean> row : matrix) {
		bToString.append("<tr>");
		for (Boolean element : row) {
		    bToString.append("<td>");
		    bToString.append(element ? "1" : "0");
		    bToString.append("</td>");
		}
		bToString.append("</tr>");
	    }
	} catch (IllegalArgumentException e) {
	    logger.log(Level.SEVERE, "index of ci not correct");
	    e.printStackTrace();
	} catch (NullPointerException e) {
	    logger.log(Level.SEVERE, "b matrix not created");
	}
	bToString.append("</table></html>");

	return bToString.toString();

    }

    public String aToString() {
	StringBuilder bToString = new StringBuilder("<html><table>");

	try {
	    if (a == null) {
		throw new NullPointerException();
	    }
	    ArrayList<ArrayList<Boolean>> matrix = a.getInfo();

	    for (ArrayList<Boolean> row : matrix) {
		bToString.append("<tr>");
		for (Boolean element : row) {
		    bToString.append("<td>");
		    bToString.append(element ? "1" : "0");
		    bToString.append("</td>");
		}
		bToString.append("</tr>");
	    }
	} catch (IllegalArgumentException e) {
	    logger.log(Level.SEVERE, "index of ci not correct");
	    e.printStackTrace();
	} catch (NullPointerException e) {
	    logger.log(Level.SEVERE, "b matrix not created");
	}
	bToString.append("</table></html>");

	return bToString.toString();
    }

    public String ciToString() {
	StringBuilder ciToString = new StringBuilder("<html><table>");

	try {
	    if (ciIndex < 0 && ciIndex > RussianMatrix.getP()) {
		throw new IllegalArgumentException();
	    }
	    if (a == null || b == null) {
		throw new NullPointerException();
	    }
	    ArrayList<ArrayList<Boolean>> matrix = null;
	    int p = RussianMatrix.getP();
	    if (ciIndex < p) {
		matrix = RussianMatrix.getCi().get(ciIndex);
	    } else {
		matrix = RussianMatrix.getResult().getInfo();
	    }

	    for (ArrayList<Boolean> row : matrix) {
		ciToString.append("<tr>");
		for (Boolean element : row) {
		    ciToString.append("<td>");
		    ciToString.append(element ? "1" : "0");
		    ciToString.append("</td>");
		}
		ciToString.append("</tr>");
	    }
	} catch (IllegalArgumentException e) {
	    logger.log(Level.SEVERE, "index of ci not correct");
	    e.printStackTrace();
	} catch (NullPointerException e) {
	    logger.log(Level.SEVERE, "cannot compute ci");
	    e.printStackTrace();
	}
	ciToString.append("</table></html>");

	return ciToString.toString();
    }

    public void generateRandomMatrixA(int size) {
	a = MatrixFactory.buildRandomRussianMatrix("square", size);
    }

    public void generateRandomMatrixB(int size) {
	b = MatrixFactory.buildRandomRussianMatrix("square", size);
    }

    public void generateFileMatrixA(String filename) {
	a = MatrixFactory.buildRussianMatrixFromFile(filename);
    }

    public void generateFileMatrixB(String filename) {
	b = MatrixFactory.buildRussianMatrixFromFile(filename);
    }

    /**
     * @return the a
     */
    public RussianMatrix getA() {
	return a;
    }

    /**
     * @param a
     *            the a to set
     */
    public void setA(RussianMatrix a) {
	this.a = a;
    }

    /**
     * @return the b
     */
    public RussianMatrix getB() {
	return b;
    }

    /**
     * @param b
     *            the b to set
     */
    public void setB(RussianMatrix b) {
	this.b = b;
    }

    /**
     * @return the ciIndex
     */
    public int getCiIndex() {
	return ciIndex;
    }

    /**
     * @param ciIndex
     *            the ciIndex to set
     */
    public void setCiIndex(int ciIndex) {
	this.ciIndex = ciIndex;
    }

    public int getP() {
	return RussianMatrix.getP();
    }

    /**
     * 
     */
    public void prev() {
	if (ciIndex == 0) {
	    ciIndex = getP();
	} else {
	    ciIndex--;
	}
    }

    public void next() {
	if (ciIndex == getP()) {
	    ciIndex = 0;
	} else {
	    ciIndex++;
	}
    }
}
