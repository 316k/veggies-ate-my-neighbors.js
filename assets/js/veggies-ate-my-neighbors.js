$(document).ready(function() {
    navigator.refresh_rate = 50;

    navigator.SpriteManager = new SpriteManager();
    navigator.CheatCodeController = new CheatCodeController();
    navigator.Joueur = new Joueur();
    navigator.TerrainController = new TerrainController();
    navigator.FenetreController = new FenetreController();
    navigator.MusiqueController = new MusiqueController();

    $('.restart').click(function() {
        window.location.reload();
    });

    // Best-fitting canvas size
    $(window).resize(function() {
        if($(window).width()/$(window).height() > $('#screen').width()/$('#screen').height()) {
            // Landscape
            $('#screen').css({
                width: 'auto',
                height: '100%',
                left: ($(window).width() - $('#screen').width())/2,
                top: ($(window).height() - $('#screen').height())/2
            });
        } else {
            // Portrait
            $('#screen').css({
                height: 'auto',
                width: '100%',
                left: 0,
                top: ($(window).height() - $('#screen').height())/2
            });
        }
    }).resize().resize(); // dumb bugfix
});
