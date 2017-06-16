package test;

public class RunTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String params ="{Groups:[{GroupName:'function'},{GroupName:'all'}],Packes:[{PackName:'tc.dm.*'},{PackName:'tc.yl.*'}]}";
		MoreGroupsExecutor mge = new MoreGroupsExecutor();
		mge.doExecute(params);
	}

}
