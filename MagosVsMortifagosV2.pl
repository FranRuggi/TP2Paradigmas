%
%
%********************************** HECHOS **********************************
%
%
tipo_personaje(mago).
tipo_personaje(mortifago).

hechizo_permitido(mago, protego).
hechizo_permitido(mago, expelliarmus).
hechizo_permitido(mago, stupefy).
hechizo_permitido(mago, petrificustotalus).
hechizo_permitido(mago, expectopatronum).

hechizo_permitido(mortifago, protego).
hechizo_permitido(mortifago, imperius).
hechizo_permitido(mortifago, crucio).
hechizo_permitido(mortifago, avadakedavra).
hechizo_permitido(mortifago, sectumsempra).

hechizo(avadakedavra, 100).
hechizo(crucio, 50).
hechizo(stupefy, 30).
hechizo(sectumsempra, 60).
hechizo(protego, 60).
hechizo(petrificustotalus, 60).
hechizo(imperius, 40).
hechizo(expelliarmus, 40).
hechizo(expectopatronum, 30).

% Nos devuelve una lista con todos los hechizos que el personaje actual puede ejecutar
% dependiendo del tipo de personaje, del nivel de magia que tenga y de los hechizos lanzados en la ronda
hechizos_disponibles(NivelMagia, TipoPersonaje, HechizosLanzados, Hechizos) :-
    findall(Hechizo,
            (hechizo(Hechizo, Costo),
             Costo =< NivelMagia,
             hechizo_permitido(TipoPersonaje, Hechizo),
             \+ member(Hechizo, HechizosLanzados)),  % Verifica que el hechizo no esté en la lista de lanzados
            Hechizos),tipo_personaje(TipoPersonaje).
