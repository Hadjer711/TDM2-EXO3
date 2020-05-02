package com.example.myapplication

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import java.util.*
import android.widget.ArrayAdapter

class InterventionCrud_Activity : AppCompatActivity() {


    private var db: DataBase? = null
    private var dao: InterventionDAO? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crud_intervention_)

        val c = Calendar.getInstance()
        val annee = c.get(Calendar.YEAR)
        val mois = c.get(Calendar.MONTH)
        val jour = c.get(Calendar.DAY_OF_MONTH)



        val addDate = findViewById<Button>(R.id.addDate)
        val spinner_type  = findViewById<Spinner>(R.id.spinner_type)
        val spinner_nom  = findViewById<Spinner>(R.id.spinner_nom)
         val InputDate=findViewById<TextView>(R.id.InputDate)
        val  spinner_nom_text = findViewById<TextView>(R.id.spinner_nom_text)
         val  spinner_type_text = findViewById<TextView>(R.id.spinner_type_text)
        val  NumInter =findViewById<TextView>(R.id.editNom)
          val btn_supp=findViewById<Button>(R.id.supp)

        val btn_ajouter = findViewById<Button>(R.id.Apliquer)
        val  mode = intent.getStringExtra("mode")
        if(mode == "modif")
        {
           val pos =intent.getIntExtra("pos",0)
            this.db = DataBase.invoke(this)
            this.dao = db?.interventionDAO()

            val intervention=  dao?.getInterv(pos+1)

            spinner_nom_text.text=intervention!![0].nom
           spinner_type_text.text=intervention!![0].type

            InputDate.text=intervention!![0].date.toString()
            NumInter.text=intervention!![0].num


        }
       if (mode=="ajout")
       {
           btn_supp.visibility= View.GONE
       }
        btn_supp.setOnClickListener{
            val num: String = NumInter.text.toString()
            val type: String = spinner_type_text.text.toString()
            val nomp: String = spinner_nom_text.text.toString()
            val date: Date = c.getTime()
            val pos =intent.getIntExtra("pos",0)

            val interv: Intervention = Intervention(pos+1, num, nomp, type, date)

            this.db = DataBase.invoke(this)
            this.dao = db?.interventionDAO()
            dao?.supprimer(interv)

            val intent = Intent(this, MainActivity::class.java)

            startActivity(intent)
        }


        btn_ajouter.setOnClickListener{


            if(mode == "ajout") {
                val num: String = NumInter.text.toString()
                val type: String = spinner_type.selectedItem.toString()
                val nomp: String = spinner_nom.selectedItem.toString()
                val date: Date = c.getTime()
                val interv: Intervention = Intervention(0, num, nomp, type, date)
                this.db = DataBase.invoke(this)
                this.dao = db?.interventionDAO()
                dao?.ajouter(interv)

                val intent = Intent(this, MainActivity::class.java)

                startActivity(intent)
            }
            else{
                if (mode=="modif") {
                    val num: String = NumInter.text.toString()
                    val type: String = spinner_type.selectedItem.toString()
                    val nomp: String = spinner_nom.selectedItem.toString()
                    val date: Date = c.getTime()
                    val pos =intent.getIntExtra("pos",0)
                    val interv: Intervention = Intervention(pos+1, num, nomp, type, date)


                    this.db = DataBase.invoke(this)
                    this.dao = db?.interventionDAO()
                    dao?.modifier(interv)
                    val intent = Intent(this, MainActivity::class.java)

                    startActivity(intent)
                }

            }
             }
        addDate.setOnClickListener {
            val datePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, annee, mois, jour ->
                InputDate!!.text="$jour-${mois+1}-$annee"
                c.set(annee, mois,jour, 0, 0)

            }, annee, mois, jour)

            datePickerDialog.show()
        }

        val NomP= arrayOf("Hadjer","Yousra","Lydia","Houichi","Boushaki")
        val Type_Interv= arrayOf("Réparation","Maintenance","Installation","Refonte","Détection du probleme")
        val adapterSpinner_N = ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_item, NomP
        )
        adapterSpinner_N.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_nom.setAdapter(adapterSpinner_N)


        val adapterSpinner_T = ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_item, Type_Interv
        )
        adapterSpinner_T.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_type.setAdapter(adapterSpinner_T)



    }



}

