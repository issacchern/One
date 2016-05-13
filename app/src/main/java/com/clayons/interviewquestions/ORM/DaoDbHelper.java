package com.clayons.interviewquestions.ORM;

import android.content.Context;

import de.greenrobot.dao.async.AsyncSession;

/**
 * Created by Issac on 5/13/2016.
 */
public class DaoDbHelper {
    private static DaoDbHelper INSTANCE = null;

    /**
     * not thread-safe
     */
    public static DaoDbHelper getInstance(Context context) {
        if(INSTANCE == null)
            INSTANCE = new DaoDbHelper(context);
        return INSTANCE;
    }

    private static final String DB_NAME = "db_file.db";
    private DaoSession daoSession;
    private AsyncSession asyncSession;

    private DaoDbHelper(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, DB_NAME, null);

        DaoMaster daoMaster = new DaoMaster(helper.getWritableDatabase());

        daoSession = daoMaster.newSession();
        asyncSession = daoSession.startAsyncSession();
    }

    public PersonDao getPersonDao(){
        return daoSession.getPersonDao();
    }

    public AsyncSession getAsyncSession(){
        return asyncSession;
    }

}