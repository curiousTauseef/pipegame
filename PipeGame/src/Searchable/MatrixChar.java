

package Searchable;

public class MatrixChar {
	
	public char[][] matrix;
	
	public MatrixChar(char[][] ch) {
		super();
		this.matrix=ch;
	}
	public MatrixChar cloneMatrix(MatrixChar m ){
		char[][] m2=new char[m.getMatrix().length][m.getMatrix()[0].length];  
		for(int i=0;i<m.getMatrix().length;i++) {
			for(int j=0;j<m.getMatrix()[0].length;j++) {
				m2[i][j]=m.getMatrix()[i][j];
			}
		}
		
		MatrixChar matrix= new MatrixChar(m2);
		return matrix;

	}
	
	public char[][] getMatrix(){return this.matrix;}
	@Override
	public String toString(){
		String s=new String();
		
		for(int i=0;i< matrix.length;i++) {
			s=s.concat( String.valueOf(matrix[i]));
		}
		return s;
	}
	
	@Override
	public boolean equals(Object obj) {
			return this.toString().equals(obj.toString());
		}
	@Override
	public int hashCode() {
		String s = this.toString();
		return s.hashCode();
	}
}
	





