package frc.helper;

	public class GS<T>
	{
		T val;
		public T get()
		{
			return val;
		}
		public void set(T newVal)
		{
			val = newVal;
		}
	}