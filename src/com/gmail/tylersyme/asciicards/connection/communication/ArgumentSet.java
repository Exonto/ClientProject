package com.gmail.tylersyme.asciicards.connection.communication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ArgumentSet
{
	private List<List<Object>> cases = new ArrayList<>();
	
	public ArgumentSet() { }
	
	public ArgumentSet addCase(Object... args)
	{
		cases.add(Arrays.asList(args));
		
		return this;
	}
	
	/**
	 * Determines if the {@code args} match one of the possible argument 
	 * configurations.
	 * 
	 * @param args
	 * @return
	 */
	public boolean isValid(Object... args)
	{
		for (List<Object> argumentCase : cases)
		{
			if (argumentCase.containsAll(Arrays.asList(args)))
			{
				return true;
			}
		}
		
		return false;
	}
	
	/*private List<Hierarchy> cases = new ArrayList<Hierarchy>();
	
	public ArgumentSet()
	{
		new ArgumentCase();
	}
	
	
	
	
	private class ArgumentCase
	{
		private Optional<ArgumentCase> parent = Optional.empty();
		
		private ArgumentCase()
		{
			
		}
	}*/

}
