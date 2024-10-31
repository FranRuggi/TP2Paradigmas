%
%
%********************************** HECHOS **********************************
%
%
hechizo_permitido(mago, protego).
hechizo_permitido(mago, expelliarmus).
hechizo_permitido(mago, stupefy).
hechizo_permitido(mago, petrificusTotalus).
hechizo_permitido(mago, expectoPatronum).

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
hechizo(petrificusTotalus, 60).
hechizo(imperius, 40).
hechizo(expelliarmus, 40).
hechizo(expectoPatronum, 30).

% Nos devuelve una lista con todos los hechizos que el personaje actual puede ejecutar
% dependiendo del tipo de personaje y del nivel de magia que tenga
hechizos_disponibles(NivelMagia, TipoPersonaje, Hechizos) :-
    findall(Hechizo,
            (hechizo(Hechizo, Costo),
             Costo =< NivelMagia,
             hechizo_permitido(TipoPersonaje, Hechizo)),
            Hechizos).
