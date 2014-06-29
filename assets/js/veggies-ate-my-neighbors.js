$(document).ready(function() {

    navigator.MusiqueController = new MusiqueController();

    $('#start-menu button.up').click(function() {
        $('.history')[0].scrollTop -= 30;
    });

    $('#start-menu button.down').click(function() {
        $('.history')[0].scrollTop += 30;
    });

    $('#start-menu button.play').click(function() {
        $('#start-menu').hide();
        $('#container').show();
        play();
    });
});

function play() {
    navigator.refresh_rate = 50;

    navigator.SpriteManager = new SpriteManager();
    navigator.CheatCodeController = new CheatCodeController();
    navigator.Joueur = new Joueur();
    navigator.TerrainController = new TerrainController();
    navigator.FenetreController = new FenetreController();

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
}
