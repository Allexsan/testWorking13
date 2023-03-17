package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.myapplication.data.Question
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private val questions = mutableListOf<Question>()
    private var currentQuestion: Int = 0

    private lateinit var tvQuestionNumber: TextView
    private lateinit var tvQuestionText: TextView
    private lateinit var btnAnswer1: Button
    private lateinit var btnAnswer2: Button
    private lateinit var btnAnswer3: Button
    private lateinit var btnAnswer4: Button


    override fun onClick(v: View?){
        v?.let{
            when (it.id){
                R.id.btnAnswer1 -> processAnswer(1)
                R.id.btnAnswer2 -> processAnswer(2)
                R.id.btnAnswer3 -> processAnswer(3)
                R.id.btnAnswer4 -> processAnswer(4)
            }
        }
    }

    private fun fillQuestions(){
        questions.run {
            this.add(Question("Имеется кортеж вида T = (4, 2, 3). Какая из операций приведёт к тому, что имя T будет ссылаться на кортеж (1, 2, 3)?","T[0] = 1", "T = (1) + T[1:]", "T = (1,) + T[1:]", "T.startswitch(1)", 3))
            this.add(Question("Для чего в Python используется встроенная функция enumerate()?","Для определения количества элементов последовательности.","Для одновременного итерирования по самим элементам и их индексам.","Для сортировки элементов по значениям id.","Для сортировки массива", 2))
            this.add(Question("Что выведет интерпретатор для следующей программы (версия Python 3.6+)?\tdef get_name_and_decades(name, age):\n" +
                    "    print(f\"My name is {name} and I'm {age / 10:.5f} decades old.\")\n" +
                    "\n" +
                    "get_name_and_decades(\"Leo\", 31)","My name is Leo and I'm 31.00000 decades old.","My name is Leo and I'm 3.1 decades old.","My name is Leo and I'm 3.10000 decades old.","My name is {name} and I'm {age / 10:.5f} decades old.", 3))
            this.add(Question("Необходимо собрать и вывести все уникальные слова из строки рекламного текста. Какой из перечисленных типов данных Python подходит лучше всего?","кортеж (tuple)","список (list)","множество (set)","словарь (dict)", 3))
            this.add(Question("Учёт зверей в зоопарке ведётся с помощью приведённого ниже списка словарей. Какая из строчек кода выведет структуру, отсортированную в порядке увеличения возрастов животных? animals = [\n" +
                    "    {'type': 'penguin', 'name': 'Stephanie', 'age': 8},\n" +
                    "    {'type': 'elephant', 'name': 'Devon', 'age': 3},\n" +
                    "    {'type': 'puma', 'name': 'Moe', 'age': 5},\n" +
                    "]","sorted(animals, key='age')","Ни один вариант не является верным, два словаря нельзя сравнивать друг с другом.","sorted(animals, key=lambda animal: animal['age'])","sorted(animals)", 3))
            this.add(Question(" Какой результат выведет следующий код? def f(a, *pargs, **kargs): print(a, pargs, kargs)\n" +
                    "f(1, 2, 3, x=4, y=5)","1, 2, 3, {'x': 4, 'y': 5}","1 (2, 3) {'x': 4, 'y': 5}","1, 2, 3, 'x=4', 'y=5'","1, 2, 3, 4, 5", 2))
            this.add(Question("Как вывести список методов и атрибутов объекта x?","help(x)","info(x)","?x","dir(x)", 4))
            this.add(Question("Как можно более кратко представить следующую запись? " +
                    "if X:\n" +
                    "    A = Y\n" +
                    "else:\n" +
                    "    A = Z","A = Y if Z else Y","A = Y if X else Z","A = X if Z else Y","A = X if Y else Z", 2))
            this.add(Question("Какая из перечисленных инструкций выполнится быстрее всего, если n = 10**6?","a = list(i for i in range(n))","a = [i for i in range(n)]","a = (i for i in range(n))","a = {i for i in range(n)}", 3))
            this.add(Question("Что выведет на экран следующий код? " +
                    "a, *b, c = [1, 2]\n" +
                    "print(a, b, c)","[1] [] [2]","Будет вызвано исключение: элементов в списке меньше, чем переменных.","1 0 2","1 [] 2", 4))
            this.add(Question("С помощью Python нужно записать данные в файл, но только в том случае, если файла ещё нет. Какой режим указать в инструкции open()?","'x'","Никакой. Нужна предварительная проверка os.path.exists()","'w'","'r'", 1))
            this.add(Question("Для чего в пакетах модулей python в файле __init__.py служит список __all__?","Для конструкторов классов, как и всё, что связано с __init__","Список определяет, что экспортировать, когда происходит импорт с помощью from *","Для перечисления переменных, которые будут скрыты для импортирования.","Для скрытия переменных их имена начинаются с одиночного подчеркивания.", 2))
            this.add(Question(" При объявлении класса с помощью оператора class что пишется в круглых скобках после имени класса?","Имена аргументов, принимаемых методом __init__.","Имена принимаемых классом аргументов.","Имена суперклассов, если класс наследуется от одного или нескольких классов.","Имена классов, порождаемых данным классом.", 3))
            this.add(Question(" Какую роль в описании метода класса выполняет декоратор @property?","Декорированный метод становится статическим, экземпляр не передаётся.","Декорированный метод становится методом класса: метод получает класс, а не экземпляр.","Значение, возвращаемое декорированным методом, вычисляется при извлечении. Можно обратиться к методу экземпляра, как к атрибуту.","При этом само значение метода size вычисляется, как метод класса.", 3))
            this.add(Question("Что выведет следующий код?" +
                    "try:\n" +
                    "    raise IndexError\n" +
                    "except IndexError:\n" +
                    "    print('Получено исключение.')\n" +
                    "else:\n" +
                    "    print('Но в этом нет ничего страшного.')","IndexError","Получено исключение.","None","TypeError", 2))
        }
    }
    private fun updateUi(){
        tvQuestionNumber.text = getString(R.string.question_number_ui, currentQuestion + 1, questions.size)
        tvQuestionText.text = questions[currentQuestion].textQuestion
        btnAnswer1.text = questions[currentQuestion].answer1
        btnAnswer2.text = questions[currentQuestion].answer2
        btnAnswer3.text = questions[currentQuestion].answer3
        btnAnswer4.text = questions[currentQuestion].answer4
    }
    private fun checkAnswer(givenId: Int) = (givenId == questions[currentQuestion].right)

    private var correctAnswerCount: Int = 0

    private fun toNextQuestion(): Boolean{
        if (currentQuestion >= questions.size - 1) return false
        currentQuestion++
        updateUi()
        return true
    }
    private fun processAnswer(givenId: Int){
        if (checkAnswer(givenId)) correctAnswerCount++
        if (!toNextQuestion()){
            Toast.makeText(this, "Игра окончена", Toast.LENGTH_SHORT)
            btnAnswer1.isEnabled = false
            btnAnswer2.isEnabled = false
            btnAnswer3.isEnabled = false
            btnAnswer4.isEnabled = false
            tvQuestionNumber.text = getString(R.string.game_over)
            tvQuestionText.text = getString(R.string.game_result, correctAnswerCount, questions.size)
            val correctProc=(correctAnswerCount*100/questions.size).toInt()
            val incorrectProc=100-correctProc
            val mess=when{
                correctProc < 50 -> tvQuestionNumber.text=getString(R.string.Ocenka_2)
                correctProc < 75 -> tvQuestionNumber.text=getString(R.string.Ocenka_3)
                correctProc < 85 -> tvQuestionNumber.text=getString(R.string.Ocenka_4)
                else -> tvQuestionNumber.text=getString(R.string.Ocenka_5)
            }
            val resultMes=getString(R.string.game_rezult_proc,correctProc,incorrectProc)
            tvQuestionText.text=resultMes
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvQuestionNumber = findViewById(R.id.tvQuestionNumber)
        tvQuestionText = findViewById(R.id.tvQuestionText)
        btnAnswer1 = findViewById(R.id.btnAnswer1)
        btnAnswer2 = findViewById(R.id.btnAnswer2)
        btnAnswer3 = findViewById(R.id.btnAnswer3)
        btnAnswer4 = findViewById(R.id.btnAnswer4)
        btnAnswer1.setOnClickListener(this)
        btnAnswer2.setOnClickListener(this)
        btnAnswer3.setOnClickListener(this)
        btnAnswer4.setOnClickListener(this)
        fillQuestions()
        updateUi()
    }
}
