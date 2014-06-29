function MusiqueController() {
    this.music = document.getElementById('music');
    this.play();

    this.music.muted = $.parseJSON(localStorage.getItem('music.mute')) || false;

    if(this.music.muted) {
        $('#mute').addClass('mute');
    }

    var self = this;

    $('#mute').click(function() {
        self.music.muted = !self.music.muted;
        $(this).toggleClass('mute');
        localStorage.setItem('music.mute', self.music.muted);
    });
}

/**
 * Plays music
 * (TODO)
 */
MusiqueController.prototype.play = function() {
    music.play();
    music.loop = true;
}
