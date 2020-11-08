Piotr Przemielewski
Projekt zaliczeniowy AiSD 2020
Temat: Wizualizacja grafu
Prowadząca: Agnieszka Mars



0. Słowem wstępu 

Projekt został stworzony w języku Java z technologią JavaFX. Środowiskiem, którym posługiwałem się przy tworzeniu go, był IntelliJ firmy JetBrains. Pliki z implementacjami poszczególnych klas znajdują się w \src\sample. Również ta ścieżka, wraz z doprecyzowaniem pliku dane.txt (src/sample/dane.txt) została użyta przy wczytywaniu danych.
Niniejsza dokumentacja przedstawia szczegółowy opis projektu, działanie poszczególnych klas i argumentuje wybory podjęte przy tworzeniu go. W ostatnim podpunkcie dokumentacji znajdują się przykłady działania projektu z różnymi danymi początkowymi.


1. Wczytywanie danych

Wczytywanie danych w programie odbywa się za pomocą pliku tekstowego .txt. W reszcie dokumentacji jako przykładowa nazwa będziemy posługiwać się nazwą dane.txt. 
Nasz program odczyta dane zawarte w pliku tekstowym tylko wtedy, gdy będą zachowane określone reguły zapisu tych danych. 

Schemat ten wygląda następująco:

TYP_GRAFU (DIRECTED/UNDIRECTED)
LICZBA_WIERZCHOŁKÓW LICZBA_KRAWĘDZI
ŹRÓDŁO CEL ETYKIETA_KRAWĘDZI
INDEKS_WIERZCHOŁKA NAZWA_WIERZCHOŁKA

Poszczególne dane oddzielone są pojedynczym znakiem spacji.
Tak więc, dla przykładu:

DIRECTED
4 2
0 1
2 3
0 KRAKOW
1 WARSZAWA
2 SUWALKI
3 OLSZTYN
 
Mówi nam, iż w tym przypadku graf będzie miał postać grafu skierowanego o 4 wierzchołkach i 2 krawędziach, gdzie krawędzie skierowane są kolejno:
	Od wierzchołka 0 do wierzchołka 1
	Od wierzchołka 2 do wierzchołka 3 
Następnie przyporządkowujemy etykiety wierzchołków tak aby:
	Indeksowi wierzchołka 0 odpowiadała etykieta „KRAKOW”
	Indeksowi wierzchołka 1 odpowiadała etykieta „WARSZAWA”
	Indeksowi wierzchołka 2 odpowiadała etykieta „SUWALKI”
	Indeksowi wierzchołka 3 odpowiadała etykieta „OLSZTYN”

Nazwy etykiet nie są obowiązkowe, tak więc zapis:

DIRECTED
4 2
0 1
2 3
 
Również będzie poprawny, wówczas jako etykiety zostaną przypisane nazwy indeksów.
W przypadku, gdy:

	Przypiszemy krawędzi błędne wierzchołki
	Przy przypisywaniu etykiet użyjemy błędnych indeksów wierzchołków

Zostanie wyrzucony wyjątek i dana operacja zostanie odrzucona, jednak nie przeszkodzi to w dalszym funkcjonowaniu programu.

2. Rozdzielenie typów grafów 

W pliku Main.java odbywa się rozdzielenie programu na klasy, w których następnie zostaną utworzone żądane grafy wraz ze wszystkimi funkcjami. 
Po stworzeniu „grupy” new Group(), do której następnie będziemy dodawać poszczególne fragmenty, które będą wyświetlane na ekranie, rozpoczynamy wczytywanie danych z pliku.
Sprawdzamy jaki typ grafu ma zostać utworzony i dla poszczególnych z nich tworzymy obiekty:

	DirectedType() dla grafu skierowanego
	UndirectedType(() dla grafu nieskierowanego

W przypadku nieznanego typu lub problemu z odczytem danych, wyrzucany jest wyjątek i program jest zakańczany.

3. Inicjalizacja grafów

Implementacja klas DirectedType{} oraz UndirectedType{} jest analogiczna, dlatego nie jest wymagane poszczególne omówienie każdej z nich.
Działanie w konstruktorze DirectedType() / UndirectedType() rozpoczyna się od utworzenia zmiennych, do których będziemy chcieli przypisać wczytane z pliku wartości.

DirectedType(Group root, String path)
UndirectedType(Group root, String path)

Jako argumenty przekazujemy zmienną „root”, która umożliwi nam dodawanie komponentów do naszej grupy elementów, które następnie zostaną wyświetlone na ekranie, oraz zmienną „path”, który posłuży nam do odczytywania danych z pliku.
Pomocniczo wczytujemy pierwsza linię z pliku z typem grafu.
Następnie przechodzimy do właściwego działania pętli, w której rozpocznie się odczytywanie danych z pliku. 
Zapisujemy do zmiennych wartości odpowiadające liczbie wierzchołków i krawędzi. W przypadku, gdy wczytane dane nie są liczbami, wyrzucany jest wyjątek i działanie programu jest zakańczane. Zależnie od typu grafu tworzony jest obiekt:

GraphList graph = new GraphList(V, E);

 lub obiekt

GraphListNON graph = new GraphListNON(V, E);

W argumentach przekazana jest liczba wierzchołów oraz liczba krawędzi.

4. Przydzielanie współrzędnych wierzchołkom

W konstruktorze poszczególnego grafu tworzy jest nowy obiekt:

DrawVertices draw = new DrawVertices(V);

którego implementacja zawarta jest w pliku DrawVertices.java.
W konstruktorze tej klasy jako argument przekazywana jest liczba wierzchołków stworzonego grafu. Korzystając z otrzymanego argumentu zostaną przydzielone współrzędne wierzchołków. 
W procesie wizualizacji grafu kluczową sprawą było wymyślenie sposobu na najsensowniejsze rozmieszczenie wierzchołków, tak aby krawędzie nie przecinały wierzchołków w drodze do celu, by krawędzie nie zginały się w zbyt wielu miejscach oraz by na miarę możliwości uniknąć przecinania się krawędzi. 
W swoim rozwiązaniu postanowiłem rozmieścić wierzchołki na okręgu, którego środek znajduje się w punkcie (640, 360), w równych odległościach od swoich sąsiadów. Rozwiązanie takie, mimo iż nie jest idealne, ze względu na niemożliwość nieprzecinania innych krawędzi, jednakowoż pozwala nam na swobodne dotarcie do wszystkich pozostały wierzchołków i uniknięcie przecinania krawędziami innych wierzchołków. Graf taki wizualnie prezentuje się elegancko i jest czytelny.  
Do przydzielenia współrzędnych wierzchołków musimy znaleźć kąt, który będzie dzielił każde dwa kolejne wierzchołki na grafie, w tym celu:
t = 360 / V
Następnie, korzystając z równań:

x = a + r * cost;
y = b + r * sint
 
oraz współrzędnych środka okręgu i promienia, który został w implementacji ustawiony na 300, jesteśmy w stanie znaleźć kolejne współrzędne wierzchołków. Współrzędne są zapisywane do tablic X[] i Y[ ].


5. Tworzenie grafów

Każdy rodzaj grafów tworzony jest na bazie list sąsiedztwa z tą różnicą, w porównaniu do standardowej implementacji, iż identyfikatory symbolizujące poszczególne wierzchołki umieszczone są w Mapie<>(), tak aby szukanie odpowiednich list następowało sprawniej. 
W pliku GraphList_Interface.java znajduje się interfejs grafu skierowanego posiadający następujące metody:
void addEdge(int v, int w, Group root);

Metoda tworząca krawędź pomiędzy wierzchołkami. Trzecim argumentem jest root, pozwalający nam na zwizualizowanie danego połączenia.

int outEdges( int v);

Metoda zwracająca liczbę wychodzących krawędzi od danego wierzchołka

int getEdges(int v);

Metoda zwracająca liczbę krawędzi wchodzących do danego wierzchołka.

int getAllVertices();

Metoda zwracająca liczbę wszystkich wierzchołków.
double[] getXvalues();

Metoda zwracająca tablicę współrzędnych X-owych.

double[] getYvalues();

Metoda zwracająca tablicę współrzędnych Y-owych.

LinkedList<Integer> listEdges(int v);

Metoda zwracająca listę identyfikatorów wierzchołków, do których dochodzą krawędzie, prowadzone od danego wierzchołka v.

LinkedList<Integer> listGetEdges(int v);

Metoda zwracająca listę identyfikatorów wierzchołków, które dochodzą do danego wierzchołka v.

public void drawAllVertices(Group root)

Metoda służąca do wizualizacji wierzchołków na ekranie.

Implementacja wszystkich wymienionych metod danego interfejsu znajduje się w pliku GraphList.java.
W pliku GraphListNON_Interface.java znajduje się interfejs grafu nieskierowanego posiadający następujące metody:

id addEdge(int v, int w, Group root);

Metoda tworząca krawędź pomiędzy wierzchołkami. Trzecim argumentem jest root, pozwalający nam na zwizualizowanie danego połączenia.

int outEdges( int v);

Metoda zwracająca liczbę wychodzących krawędzi od danego wierzchołka

LinkedList<Integer> listEdges(int v);

Metoda zwracająca listę identyfikatorów wierzchołków, z którymi połączony jest wierzchołek v.

int getAllVertices();

Metoda zwracająca liczbę wszystkich wierzchołków.

double[] getXvalues();

Metoda zwracająca tablicę współrzędnych X-owych.

double[] getYvalues();

Metoda zwracająca tablicę współrzędnych Y-owych.

public void drawAllVertices(Group root)

Metoda służąca do wizualizacji wierzchołków na ekranie.

Implementacja wszystkich wymienionych metod danego interfejsu znajduje się w pliku GraphListNON.java.

6. Tworzenie krawędzi.

Po tym, jak obiekt reprezentujący wybrany przez nas graf, zostanie utworzony, rozpoczyna się etap wczytywania danych, reprezentujących połączenia między wierzchołkami. Działanie wciąż odbywa się w pliku DirectedType.java / UndirectedType.java.
Wczytywanie danych o krawędziach polega na wczytaniu z pliku całej linii danych, a następnie odseparowaniu ich i umieszczeniu w tablicy stringów. Jako separator służy nam tu znak spacji.
W przypadku, gdy któryś z wczytanych identyfikatorów wierzchołków nie jest reprezentowanych przez liczbę, wyrzucany jest wyjątek, a działania związane z obecnie przerabianą linią danych są pomijane. Program może działać dalej, a błędnie zapisane połączenie nie zostanie pokazane na ekranie. 
W przypadku, gdy utworzona tablica stringów ma rozmiar > 2, oznacza to, iż chcemy, by dana krawędź była reprezentowana przez ustaloną etykietę. Rozważamy liczbę >2, w razie gdybyśmy chcieli nazwać krawędź, przykładowo: „bardzo blisko”. Jeżeli rozmiar tablicy jest równy 2, wówczas oznacza to, iż nie chcemy by dana krawędź zawierała etykietę. 
Rozpoczyna się wówczas działania metody:

graph.addEdge(vertex1, vertex2, root);

Metoda ta, prócz dodania do poszczególnych list sąsiedztwa danych wierzchołków, rozpoczyna działanie klasy, której zadaniem jest naniesienie krawędzi na ekran. 

7. Wizualizacja krawędzi 

W metodzie służącej do dodania połączenia między wierzchołkami tworzony jest obiekt DrawLines():

DrawLines lines = new DrawLines(X[v], Y[v],
                                X[w], Y[w],
                                root, GraphType.DIRECTED);

Jako argumenty przekazujemy współrzędne poszczególnych wierzchołków, które uzyskujemy z tablic X[ ] i Y [ ], root, dzięki któremu będziemy mogli dodać krawędź na ekran oraz typ grafu, który jest obecnie rozpatrywany. Ostatni argument jest kluczowy, ponieważ od niego zależy czy na końcu krawędzi będzie znajdować się grot.
Implementacja klasy DrawLines{ } znajduje się w pliku DrawLines.java. 
Poszczególne typy grafu zapisane są w klasie GraphType{ } w pliku GraphType.java, która zawiera typy wyliczeniowe symbolizujące poszczególne typy grafów. 
Działanie konstruktora klasy DrawLines{ } rozpoczyna się od sprawdzania jakiego typu krawędź ma zostać dodana. Jeżeli rozważany przez nas graf jest grafem nieskierowanym, wówczas prowadzimy linię od współrzędnych początkowych do współrzędnych końcowych, dodajemy linię do grupy wyświetlanych elementów i działanie kończy się. 
W przypadku, gdy rozpatrujemy graf skierowany, dodajemy krawędź do grupy wyświetlanych elementów. Następnie używający odpowiednich funkcji trygonometrycznych i posługując się kątem, który znajduje się między tymi dwoma wierzchołkami, znajdujemy punkty, które posłużą nam jako wierzchołki naszego grotu. Operację powtarzamy, tak aby grot nie nachodził na powierzchnię wierzchołka docelowego, wówczas tworzymy trójkąt z otrzymanych punktów i dodajemy go do grupy wyświetlanych elementów. 

8.Tworzenie etykiet krawędzi

Po dodaniu poszczególnych krawędzi do wyświetlanych elementów rozważamy przypadek, gdy krawędź posiada etykietę. Program wraca do implementacji poszczególnego typu grafu w pliku DirectedType.java / UndirectedType.java. 
Tworzymy stringa, do którego dodajemy kolejne komponenty etykiety, w razie, gdyby jego długość była większa niż jednoczłonowa. Następnie rozważamy dwa odrębne przypadki:
	Wierzchołki są położone bezpośrednio obok siebie
	Wierzchołki są oddalone od siebie w odległości większej niż jeden wierzchołek

Warunek tej jest konieczny, ponieważ etykiety są wyświetlane wówczas w innych pozycjach. Wynika to z tego, iż przykładowo, jeżeli postanowimy wyświetlać etykiety mniej-więcej w połowie długości krawędzi, to będzie to optymalne rozwiązane dla krawędzi prowadzonych do wierzchołków sąsiednich (takich, które na planszy znajdują się obok siebie). Pomysł ten jednak zawodzi dla dłuższych krawędzi, przykładowo prowadzonych do wierzchołków znajdujących się po drugiej stronie okręgu, gdyż bardzo łatwo wówczas o nachodzenie etykiet na siebie, ponieważ wszystkie będą oscylowały wokół środka (640, 360).
Zgodnie z powyższym, etykiety będą znajdować się na pozycjach:
	Jeżeli wierzchołki są „sąsiadami”: w połowie odległości między wierzchołkami 
	Jeżeli wierzchołki nie są „sąsiadami”: w 4/5 odległości od wierzchołka początkowego do wierzchołka docelowego. 

Tworzony jest wówczas obiekt:
new DrawLineInfo(X[vertex1], Y[vertex1], X[vertex2], Y[vertex2], nameE, root, true);

lub
new DrawLineInfo(X[vertex1], Y[vertex1], X[vertex2], Y[vertex2], nameE, root, false);

Ostatni argument informuje nas, czy wierzchołki leżą bezpośrednio obok siebie na planszy. 

9. Wizualizacja etykiet krawędzi i wizualizacja wierzchołków

Implementacja klasy, zajmującej się wizualizacją etykiety, znajduje się w pliku DrawLineInfo.java. 
W konstruktorze klasy przyjmowane są argumenty, które omówiliśmy w poprzednim podpunkcie. Po sprawdzeniu, dla którego przypadku będziemy wizualizować etykietę, w zależności od tego, gdzie znajduje się wierzchołek docelowy, a gdzie wierzchołek źródłowy, przesuwamy etykietę względem krawędzi. Następnie pogrubiamy etykietę (efekt „bold”), nadajemy jej kolor i dodajemy do grupy wyświetlanych elementów.
Po tym, jak krawędzie z lub bez etykiet zostały utworzone, uruchamiamy metodę drawAllVertices() obiektu graph, która doda nam do grupy wyświetlanych elementów wierzchołki. Koordynaty są nam już znane, wystarczy więc zwizualizować wierzchołki. Do tego użyjemy koła (new Circle()) o promieniu R = 10px. Ustalamy kolor koła i dodajemy go do grupy.

cercle = new Circle();
cercle.setCenterX(X[i]);
cercle.setCenterY(Y[i]);
cercle.setRadius(10);
cercle.setFill(Color.GRAY);
root.getChildren().add(cercle);


10. Tworzenie etykiet wierzchołków

Po utworzeniu krawędzi z lub bez etykiet, przechodzimy do tworzenia etykiet wierzchołków. Tworzymy tablicę stringów:
String[] places = new String[X.length];

w której będą znajdować się nasze etykiety wierzchołków. 
Odczytujemy kolejne linie z pliku do momentu, aż jest to możliwe. 
Po pobraniu linii rozdzielamy poszczególne wyrazy na oddzielne stringi i zapisujemy do pomocniczej tablicy. Następnie, podobnie jak było w przypadku etykiet krawędzi, rozważamy przypadek, gdy etykieta wierzchołka jest dwu lub więcej członowa. W przypadku, gdy tak jest, łączymy poszczególne wyrazy w jednego stringa. Sprawdzamy, czy pobrany indeks wierzchołka, do którego chcemy przypisać etykietę, nie jest większy lub równy liczbie wierzchołków. W razie, gdy tak jest, wypisujemy informację na ekran i rozważany przez nas przypadek jest porzucany. Na pozycję pobranego indeksu w tablicy places[ ] przypisujemy odpowiadający mu string. 
Rozważamy również przypadek, gdy odczytany przez nas indeks wierzchołka nie jest liczbą, wówczas wyrzucany jest wyjątek. 
Następnie, w pętli sprawdzamy, którym pozycjom w tablicy places[ ] został już przypisany string. Tym, którym jeszcze żadna nazwa nie została przyporządkowana (== null), przypisujemy numer indeksu wierzchołka. 
Posiadamy uzupełnioną tablicę stringów places[ ], rozpoczynamy więc tworzenie nowego obiektu, którego celem będzie wizualizacja tych etykiet:

new DrawIndex(X, Y, places, root);

11. Wizualizacja etykiet wierzchołków

Implementacja konstruktora DrawIndex() klasy DrawIndex{} znajduje się w pliku DrawIndex.java.
Nanoszenie etykiet wierzchołków na ekran rozpatrujemy dla czterech różnych sytuacji, w zależności od ćwiartki, w której znajduje się wierzchołek. Etykieta umieszczana jest na zewnątrz względem okręgu, na którym opisane są wierzchołki. 
Etykieta jest pogrubiana (efekt „bold”) oraz nadajemy jej kolor, po czym dodajemy ją do grupy wyświetlanych elementów.

12. Tworzenie ekranu wraz ze wszystkimi elementami 

Po tym, jak wszystkie wcześniejsze kroki tworzenia wizualizacji grafu zostały wykonane, algorytm powraca do klasy Main{} w pliku Main.java, do metody start(), od której rozpoczęło się działanie programu.
W końcowym etapie ustalamy nazwę wyświetlanego okna oraz tworzymy nową scenę o wymiarach 1280x720 i dodajemy do niej grupę root, posiadającą wszystkie elementy, potrzebne do wizualizacji grafu.
Graf jest wyświetlany w oknie do momentu zamknięcia go krzyżykiem w górnym, prawym rogu.



 

 




 

 





 





 









 



 









 


 














