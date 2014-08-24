package mp.edu.hs.trier.de.model;

/**
 * The Interface for the Model
 * 
 * @version 1.0
 * @author Philippe Kayser
 *
 */

public interface IShapeModelChanged {

	public void shapeChanged();
	public void rotationChanged();
	public void strokeChanged();
	public void fillChanged();
	public void sizeChanged();
	public void nameChanged(int index);
}
