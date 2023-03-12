# Tema2_APD
*Alexandrescu Marius-Cristian - 331CB*

*Tema 2 - APD*

Am inceput prin a pune in liste datele din cele 2 fisiere, astfel lucrand mai usor cu ele.
Pornesc apoi maxim p thread-uri si parcurg elementele din prima lista (id_comanda, nr_comenzi).
Impart thread-urile pentru a citi doar anumite parti din lista. Pentru fiecare element citit,
voi porni maxim p thread-uri (cu ajutorul semaforului) pentru a cauta prin cea de a doua lista
(cautam produsele din comanda respectiva). Scriu in fisierul "order_products_out.txt" datele
citite la care adaug "shipped". Folosesc un semafor pentru a nu scrie de mai multe ori acelasi
lucru in fisier. Cand acel nivel de thread-uri se termina, scriu in fisierul "orders_out.txt"
datele curente si "shipped". Cand toate thread-urile se termina, inchid cele 2 FileWritere.
