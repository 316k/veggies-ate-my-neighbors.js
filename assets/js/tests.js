assertEquals('attaque des combattants', 4, function() {
    var a = new Combattant();
    var b = new Combattant();

    a.gentil = false;
    b.gentil = true;

    b.cibles.push(a);
    b.attaquer(1);

    return a.vie;
});

assertEquals('clone_json', false, function() {
    var a = {gentil: false};
    var b = clone_json(a);
    b.gentil = true;

    return a.gentil;
});

assertEquals('Combattant.clone', false, function() {
    var a = new Combattant();
    a.gentil = false;
    var b = a.clone();
    b.gentil = true;

    return a.gentil;
});

assertEquals('Rectangle.intersects 1', false, function() {
    var a = new Rectangle(0, 0, 150, 150);
    var b = new Rectangle(151, 151, 100, 100);

    return a.intersects(b);
});

assertEquals('Rectangle.intersects 2', false, function() {
    var a = new Rectangle(0, 0, 150, 150);
    var b = new Rectangle(50, 151, 100, 100);

    return a.intersects(b);
});

assertEquals('Rectangle.intersects 3', false, function() {
    var a = new Rectangle(0, 0, 150, 150);
    var b = new Rectangle(151, 50, 100, 100);

    return a.intersects(b);
});

assertEquals('Rectangle.intersects 4', true, function() {
    var a = new Rectangle(0, 0, 150, 150);
    var b = new Rectangle(10, 50, 100, 100);

    return a.intersects(b);
});

assertEquals('Rectangle.intersects 5', false, function() {
    var a = new Rectangle(0, 0, 150, 150);
    var b = new Rectangle(150, 0, 150, 150);

    return a.intersects(b);
});

assertEquals('Rectangle.contains_point', true, function() {
    var a = new Rectangle(0, 0, 150, 150);
    var b = new Point(10, 50);

    return a.contains_point(b);
});

assertEquals('Array.remove_elements', JSON.stringify([123, 434, 555, 209]), function() {
    var a = [123, 23, 434, 555, 12, 209, 99];
    var b = [23, 12, 99];

    a = array_remove_elements(a, b);

    return JSON.stringify(a);
});
