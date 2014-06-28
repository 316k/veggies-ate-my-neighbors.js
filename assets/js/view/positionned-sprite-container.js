/**
 * SpriteContainer avec une position
 * @author Nicolas Hurtubise, Guillaume Riou
 */
function PositionnedSpriteContainer(position, image, animation) {
    SpriteContainer.apply(this);
    /**
     * Point Position du spriteContainer.
     */
    this.position;

    this.image = image;
    this.animation = animation;
    this.position = position;
}

PositionnedSpriteContainer.prototype = new SpriteContainer();
