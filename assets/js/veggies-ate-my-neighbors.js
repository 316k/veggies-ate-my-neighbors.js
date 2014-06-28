$(document).ready(function() {
    navigator.SpriteManager = new SpriteManager();
    navigator.JoueurController = new JoueurController();
    navigator.CheatCodeController = new CheatCodeController();
    navigator.TerrainController = new TerrainController();
    navigator.FenetreController = new FenetreController();
    navigator.MusiqueController = new MusiqueController();

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

        $('#notifications').css('top', ($(window).height() - $('#notifications').height())/2);
    }).resize().resize(); // dumb bugfix
});