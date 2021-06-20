# BottomNavigation

BottomNavigation é uma barra de navegação localizada na área inferior da aplicação, e serve para tornar mais fácil a navegação do usuário pela aplicação e o seu uso é recomendado para aplicativos que possuem de 3 a 5 destinos. E o seguinte artigo irá explicar como fazer uso dessa navegação em sua aplicação.

 

É recomendado utilizar BottomNavigationView juntamente de fragments para uma transição entre telas mais otimizada, e para que ocorra a transição entre fragments deverá ser criado um "fragment_container" que será responsável pela exibição dos diferentes conteúdos desejados, exemplo:

```jsx
<fragment
        android:id="@+id/fragment_container"
        android:name="androidx.navigation.fragment.NavHostFragment"
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_weight="1"
        />
```

Para se preencher um BottomNavigation com as opções de menu é necessário especificar um recurso de menu dentro do XML do BottomNavigation, exemplo:

```jsx
<com.google.android.material.bottomnavigation.BottomNavigationView
      android:id="@+id/bottom_navigation"
      android:layout_width="match_parent"
      android:layout_height="wrap_content"
      app:menu="@menu/menu" // Aqui deverá ser especificado o recurso de menu
/>
```

Exemplo de um recurso de menu:

```jsx
<menu xmlns:android="http://schemas.android.com/apk/res/android">
    <item
        android:title="Item 1"
        android:id="@+id/item1"
        android:icon="@drawable/ic_android_black_24dp"
        />

    <item
        android:title="Item 2"
        android:id="@+id/item2"
        android:icon="@drawable/ic_android_black_24dp"
        />

    <item
        android:title="Item 3"
        android:id="@+id/item3"
        android:icon="@drawable/ic_android_black_24dp"
        />
</menu>
```

Para realizar ações quando um usuário clicar em um item do BottomNavigation deverá ser utilizado o seguinte ouvinte **BottomNavigation.OnNavigationItemSelectedListener,** onde através dele será possível identificar o item clicado pelo id que foi definido no recurso de menu, exemplo:

```jsx
BottomNavigationView.OnNavigationItemSelectedListener { item ->
    when(item.itemId) {
        R.id.item1 -> {
            // Código que será executado quando o item1 for clicado
	          true
        }
        R.id.item2 -> {
            // Código que será executado quando o item2 for clicado
            true
        }
        else -> false
    }
}
```

## Exemplo 1 de como trocar telas usando BottomNavigation.

Para realizar transições entre os fragments utilize a function a seguir:

```jsx
private fun switchFragment(fragment : Fragment){
    supportFragmentManager
        .beginTransaction()
        .replace(R.id.fragment_container, fragment)
        .commit()
}
```

Esta function irá realizar a substituição de fragments através do supportFragmentManager onde deverá ser passado o fragment que será posto no lugar como parâmetro e na função .replace(), deverá ser passado o id do "fragment_container" e o novo fragment, após isso utilizar .commit() para efetuar a troca.

Então o código final deverá ficar parecido com isso:

```jsx
BottomNavigationView.OnNavigationItemSelectedListener { item ->
    when(item.itemId) {
        R.id.item1 -> {
						switchFragment(item1())
	          true
        }
        R.id.item2 -> {
            switchFragment(item2())
	          true
        }
				R.id.item3 -> {
            switchFragment(item3())
	          true
        }
        else -> false
    }
}
```

Caso tenha seguido arrisca esse tutorial você perceberá que não terá um fragment selecionado inicialmente, e para que haja um fragment inicial você poderá fazer de duas formas, a primeira seria  apenas chamar a function switchFragment(item1()) dentro do onCreate, exemplo:

```jsx
override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNav = findViewById(R.id.bottom_navigation)

        switchFragment(item1()) // Aqui deverá ser chamado a switchFragment() setando o item1 como um fragment "inicial"
        initBottomNavigation()
    }
```

A segunda forma seria criando um recurso de navigation, no qual você iria importar os 3 fragments que serão utilizados na BottomNavigation e definindo um como o destino inicial, exemplo:

<p align="center"><img src="https://s3.us-west-2.amazonaws.com/secure.notion-static.com/73cf065d-1ef9-4fc3-848b-b726214bb43b/Untitled.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAT73L2G45O3KS52Y5%2F20210620%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20210620T164523Z&X-Amz-Expires=86400&X-Amz-Signature=12c7fcdb21a9a149c21b6c1a080db38db66ba911d3716de3308591da75075e15&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22Untitled.png%22">

Após isso você terá que definir no "fragment_container" um navGraph, que será apontado ao recurso de navegação que acabamos de criar, exemplo:

```jsx
<fragment
        android:id="@+id/fragment_container"
        android:name="androidx.navigation.fragment.NavHostFragment"
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_weight="1"
        app:navGraph="@navigation/navigation_fragment" //Aqui deverá ser especificado o recurso de navegação
/>
```

## Exemplo 2 de como trocar telas usando BottomNavigation.

A segundo forma de realizar a troca entre telas é utilizar navController e para isso devemos adicionar o seguinte código no nosso XML, exemplo:

```jsx
<fragment
        android:id="@+id/fragment_container"
        android:name="androidx.navigation.fragment.NavHostFragment"
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_weight="1"
        app:defaultNavHost="true" // É necessario adicionar para defini-lo como uma navController
        app:navGraph="@navigation/navigation_fragment" />
```

Após isso já poderemos fazer as alterações em nosso código kotlin para que ele realize as transições, e de uma forma bem simples podemos realizar as transições com uma única linha, exemplo:

```jsx
override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNav = findViewById(R.id.bottom_navigation)

        bottomNav.setupWithNavController(findNavController(R.id.fragment_container)) // Onde aqui deverá ser realizado um findNavController que irá apontar para nosso fragment_container
    }
```

**AVISO:** Para realizar as transições corretamente o ids definido no recurso de menu deverão ser os mesmos ids definido nos fragments adicionado no recurso de navegação

## **E é isso! Sua BottomNavigation já estará funcionando e direcionando o usuário para os diferentes fragments criados!**
