import org.apache.commons.io.FileUtils;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.database.Database;
import org.pentaho.di.core.database.DatabaseMeta;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.core.exception.KettleXMLException;
import org.pentaho.di.core.plugins.PluginRegistry;
import org.pentaho.di.core.plugins.StepPluginType;
import org.pentaho.di.core.xml.XMLHandler;
import org.pentaho.di.repository.kdr.KettleDatabaseRepository;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransHopMeta;
import org.pentaho.di.trans.TransMeta;
import org.pentaho.di.trans.step.StepMeta;
import org.pentaho.di.trans.steps.insertupdate.InsertUpdateMeta;
import org.pentaho.di.trans.steps.tableinput.TableInputMeta;
import org.w3c.dom.Document;

import java.io.File;
import java.io.IOException;

public class Test {
    public static String bjdt_tablename="dt_ogg_test";
    public static String kettle_tablename="dt_ogg_test";
    public static final String[] databasesXML = {
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                    "<connection>" +
                    "<name>bjdt</name>" +
                    "<server>192.168.106.174</server>" +
                    "<type>Oracle</type>" +
                    "<access>Native</access>" +
                    "<database>orcl</database>" +
                    "<port>1521</port>" +
                    "<username>smartecap_data</username>" +
                    "<password>123456</password>" +

                    "<attributes>"+
                    "<attribute><code>EXTRA_OPTION_ORACLE.characterEncoding</code><attribute>utf-8</attribute></attribute>"+
                    "</attributes>"+

                    "</connection>",
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                    "<connection>" +
                    "<name>kettle</name>" +
                    "<server>192.168.11.99</server>" +
                    "<type>Mysql</type>" +
                    "<access>Native</access>" +
                    "<database>test</database>" +
                    "<port>3306</port>" +
                    "<username>root</username>" +
                    "<password>123456</password>" +

                    "<attributes>"+
                    "<attribute><code>EXTRA_OPTION_MYSQL.characterEncoding</code><attribute>utf-8</attribute></attribute>"+
                    "</attributes>"+

                    "</connection>"
    };

    public static void main(String[] args){
        try {
            KettleEnvironment.init();
            Test test=new Test();

            TransMeta transMeta=test.generateMyOwnTrans();
            String transXml=transMeta.getXML();
            String transName="update_insert_trans.ktr";
            File file=new File(transName);
            FileUtils.writeStringToFile(file,transXml,"UTF-8");

            String xmls = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> \n" + transMeta.getXML().toString();
            Document doc= XMLHandler.loadXMLString(xmls);


            //Database database2=new Database(null,transMeta.findDatabase("kettle"));

            //database2.connect();
            Trans trans=new Trans(transMeta);
            trans.execute(null);
            trans.waitUntilFinished();

            //database2.disconnect();
        } catch (KettleException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public TransMeta generateMyOwnTrans() throws KettleXMLException {
        System.out.println("*********************************start to generate my own transformation *******************************");
        TransMeta transMeta=new TransMeta();
        transMeta.setName("insert_update");
        for(int i=0;i<databasesXML.length;i++){
            DatabaseMeta databaseMeta=new DatabaseMeta(databasesXML[i]);
            transMeta.addDatabase(databaseMeta);
        }

        PluginRegistry registry=PluginRegistry.getInstance();
        TableInputMeta tableInputMeta=new TableInputMeta();
        String tableInputPluginId=registry.getPluginId(StepPluginType.class,tableInputMeta);
        DatabaseMeta database_bjdt=transMeta.findDatabase("bjdt");
        tableInputMeta.setDatabaseMeta(database_bjdt);
        String select_sql="SELECT * FROM "+bjdt_tablename;
        tableInputMeta.setSQL(select_sql);

        StepMeta tableInputMetaStep=new StepMeta(tableInputPluginId,"table input",tableInputMeta);

        tableInputMetaStep.setDraw(true);
        tableInputMetaStep.setLocation(100,100);
        transMeta.addStep(tableInputMetaStep);
        InsertUpdateMeta insertUpdateMeta=new InsertUpdateMeta();
        String insertUpdateMetaPluginId=registry.getPluginId(StepPluginType.class,insertUpdateMeta);
        DatabaseMeta database_kettle=transMeta.findDatabase("kettle");
        insertUpdateMeta.setDatabaseMeta(database_kettle);
        insertUpdateMeta.setTableName(kettle_tablename);
        insertUpdateMeta.setKeyLookup(new String[]{"ID"});
        insertUpdateMeta.setKeyStream(new String[]{"ID"});
        insertUpdateMeta.setKeyStream2(new String[]{""});
        insertUpdateMeta.setKeyCondition(new String[]{"="});
        String[] updatelookup={"ID","name"};
        String [] updatestream={"id","name"};
        Boolean[] updateornot={false,true};


        insertUpdateMeta.setUpdateLookup(updatelookup);
        insertUpdateMeta.setUpdateStream(updatestream);
        insertUpdateMeta.setUpdate(updateornot);

        StepMeta insertUpdateStep=new StepMeta(insertUpdateMetaPluginId,"insert_Update__",insertUpdateMeta);
        insertUpdateStep.setDraw(true);
        insertUpdateStep.setLocation(250,100);
        transMeta.addStep(insertUpdateStep);
        transMeta.addTransHop(new TransHopMeta(tableInputMetaStep,insertUpdateStep));
        System.out.println("********************* the end *******************");
        return transMeta;
    }
}
