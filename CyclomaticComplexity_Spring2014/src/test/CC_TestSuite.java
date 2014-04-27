package test;

//import jframetest.SortFrame;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.swing.JPanel;




import org.junit.Ignore;
import org.junit.internal.AssumptionViolatedException;
import org.junit.internal.runners.model.EachTestNotifier;
import org.junit.rules.RunRules;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.Runner;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
import org.junit.runner.notification.RunNotifier;
import org.junit.runner.notification.StoppedByUserException;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.Suite;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;
import org.junit.runners.model.TestClass;

public class CC_TestSuite extends Suite {
	/**
	 * Annotation for a method which provides parameters to be injected into the
	 * test class constructor by <code>Parameterized</code>
	 */
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.METHOD)
	
	public static @interface Parameters
	{
	}

	
	private class TestClassRunnerForParameters extends BlockJUnit4ClassRunner 
	{
		private final Object displayObject;
		private final Object[] parameterList;
		private int currentTestMaxPoints;
		private String currentTestPointsEarnedString_Success;
		private String currentTestPointsEarnedString_Failure;
		private String currentTestPointsEarnedString;

		TestClassRunnerForParameters(Class<?> type, Object[] parameterList, Object displayObject) throws InitializationError 
		{
			super(type);
			this.parameterList= parameterList;
			this.displayObject = displayObject;
		}
		

		@Override
		public void run(final RunNotifier notifier) {
			System.out.println("TestClassRunnerForParameters.run()");
			EachTestNotifier testNotifier= new EachTestNotifier(notifier,
					getDescription());
			try {
				Statement statement= classBlock(notifier);
				statement.evaluate();
			} catch (AssumptionViolatedException e) {
				testNotifier.fireTestIgnored();
			} catch (StoppedByUserException e) {
				throw e;
			} catch (Throwable e) {
				testNotifier.addFailure(e);
			}
			afterAllTests();
		}
		
		private void afterAllTests()
		{
			ModalDialog dlg = new ModalDialog(new JPanel(), "title", "cyclomatic complexity statistic for single student");
			System.out.println("AFTER ALL TESTS FOR SINGLE STUDENT");
			//throw new RuntimeException("afterAllTests()");
		}

		@Override
		public Object createTest() throws Exception {
			return getTestClass().getOnlyConstructor().newInstance(parameterList);
		}

		@Override
		protected String getName() {
			return String.format("[%s]", displayObject);
		}

		@Override
		protected String testName(final FrameworkMethod method) {
			return String.format("%s[%s]", method.getName(), displayObject);
			//return String.format("%s[%s]<%s>", method.getName(), displayObject, currentTestPointsEarnedString);
		}

		@Override
		protected void validateConstructor(List<Throwable> errors) {
			validateOnlyOneConstructor(errors);
		}

		@Override
		protected Statement classBlock(RunNotifier notifier) {
			return childrenInvoker(notifier);
		}
	}

	private final ArrayList<Runner> runners= new ArrayList<Runner>();

	/**
	 * Only called reflectively. Do not use programmatically.
	 */
	public CC_TestSuite(Class<?> klass) throws Throwable {
		super(klass, Collections.<Runner>emptyList());
		List<Object[]> parametersList= getParametersList(getTestClass());
		for (int i= 0; i < parametersList.size(); i++)
		{
			assert parametersList.size() > i : "parametersList.size() = " + parametersList.size() + " <= " + i + " = i!";
			assert parametersList.get(i).length > 0 : "parametersList.get(" + i + ").length = " + parametersList.get(i).length + " <= 0!";
			runners.add(new TestClassRunnerForParameters(getTestClass().getJavaClass(), parametersList.get(i), parametersList.get(i)[0]));
		}
	}
	
	@Override
	public void run(final RunNotifier notifier) {
		System.out.println("CC_TestSuite.run()");
		EachTestNotifier testNotifier= new EachTestNotifier(notifier,
				getDescription());
		try {
			Statement statement= classBlock(notifier);
			statement.evaluate();
		} catch (AssumptionViolatedException e) {
			testNotifier.fireTestIgnored();
		} catch (StoppedByUserException e) {
			throw e;
		} catch (Throwable e) {
			testNotifier.addFailure(e);
		}
		afterAllTests();
	}
	
	private void afterAllTests()
	{
		ModalDialog dlg = new ModalDialog(new JPanel(), "title", "cyclomatic complexity statistic all students");
		System.out.println("AFTER ALL TESTS FOR ALL STUDENTS");
		//throw new RuntimeException("afterAllTests()");
	}

	@Override
	protected List<Runner> getChildren() {
		return runners;
	}

	@SuppressWarnings("unchecked")
	private List<Object[]> getParametersList(TestClass klass)
	throws Throwable {
		return (List<Object[]>) getParametersMethod(klass).invokeExplosively(null);
	}

	private FrameworkMethod getParametersMethod(TestClass testClass)
	throws Exception {
		List<FrameworkMethod> methods= testClass.getAnnotatedMethods(Parameters.class);
		for (FrameworkMethod each : methods) {
			int modifiers= each.getMethod().getModifiers();
			if (Modifier.isStatic(modifiers) && Modifier.isPublic(modifiers))
				return each;
		}

		throw new Exception("No public static parameters method on class " + testClass.getName());
	}
}