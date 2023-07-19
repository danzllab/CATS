#@UIService ui
#@ File    (label="Landmark file") landmarksPath
#@ Integer (label="Dimensions 2D/3D?", value=3) nd
#@ Boolean (label="Inverted?", value=false) invert

import mpicbg.models.*;
import bigwarp.landmarks.LandmarkTableModel;


def fitTransform( Model model, LandmarkTableModel tableModel)
{

    int numActive = tableModel.numActive();
    int ndims = tableModel.getNumdims();
    
    double[][] mvgPts = new double[ ndims ][ numActive ];
    double[][] tgtPts = new double[ ndims ][ numActive ];
    
    tableModel.copyLandmarks( tgtPts, mvgPts );
    
    double[] w = new double[ numActive ];
    Arrays.fill( w, 1.0 );
    
    try {
        model.fit( tgtPts, mvgPts, w );
    } catch (NotEnoughDataPointsException e) {
        e.printStackTrace();
    } catch (IllDefinedDataPointsException e) {
        e.printStackTrace();
    }
}

ltm = new LandmarkTableModel( nd );
try 
{
	ltm.load( landmarksPath );
} catch ( IOException e )
{
	ui.showDialog("Landmark file does not exist...")
	e.printStackTrace();
	return;
}

switch (nd) 
{
    case 2 : sim_model = new SimilarityModel2D(); break;
    case 3 : sim_model = new SimilarityModel3D(); break;
    default : ui.showDialog("Number of Dimensions not supported"); return;    
}


fitTransform( sim_model, ltm );

double[][] A = new double[3][4];
sim_model.toMatrix(A);

ef = Math.sqrt(A.collect { it[0] }.collect { it * it }.sum())

if (invert) {
    ef = 1 / ef;
}

output_str = sprintf("Expansion factor: %5.4f", ef)
ui.showDialog(output_str)