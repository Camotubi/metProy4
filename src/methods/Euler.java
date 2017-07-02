package methods;

import java.util.ArrayList;

import camo_calc.Function;

public class Euler
{
	private double y0;
	private double x0;
	private double h=0.1;
	private Function dydx;
	
	public Double[][] solve( Double[] initial,double h, double xMax) throws Exception
	{
		ArrayList<Double> dydxs = new ArrayList<Double>();
		ArrayList<Double> ys = new ArrayList<Double>();
		ArrayList<Double> xs = new ArrayList<Double>();
		double xN = initial[0];
		double yN = initial[1];
		int n=0;
		Double vals[];
		if(getDydx().getVars().size()==1)
		{
			if(getDydx().getVars().get(0)=='y')
			{
				vals = new Double[] {yN};
			}
			else
			{
				vals = new Double[] {xN};
			}
		}
		else
		{
			if(getDydx().getVars().get(0)=='y')
			{
				vals = new Double[] {yN,xN};
			}
			else
			{
				vals = new Double[] {xN,yN};
			}
		}
		
		
		double dydxN = this.getDydx().evaluate(vals);
		dydxs.add(dydxN);
		ys.add(yN);
		xs.add(xN);
		
		while(xN<xMax)
		{
			System.out.println(n);
			n++;
			yN= yN+h*dydxN;
			xN= xN + h;
			if(getDydx().getVars().size()==1)
			{
				if(getDydx().getVars().get(0)=='y')
				{
					vals = new Double[] {yN};
				}
				else
				{
					vals = new Double[] {xN};
				}
			}
			else
			{
				if(getDydx().getVars().get(0)=='y')
				{
					vals = new Double[] {yN,xN};
				}
				else
				{
					vals = new Double[] {xN,yN};
				}
			}
			dydxN = this.getDydx().evaluate(vals);
			dydxs.add(dydxN);
			ys.add(yN);
			xs.add(xN);
		}
		Double outputMatrix[][] = new Double[ys.size()][4];
		for(int r = 0 ; r < ys.size();r++ )
		{
			outputMatrix[r][0]=r+0.0;
			outputMatrix[r][1]=xs.get(r);
			outputMatrix[r][2]=ys.get(r);
			outputMatrix[r][3]=dydxs.get(r);
		}
		return outputMatrix;
	}

	public Function getDydx() {
		return dydx;
	}

	public void setDydx(Function dydx) {
		this.dydx = dydx;
	}
}