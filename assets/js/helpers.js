/**
 * Deep (recursive) clone of a JSON object
 */
function clone_json(object) {
    var clone = {};

    if(object === undefined) {
        return undefined;
    }

    // Clones functions
    if(object.__proto__ == clone_json.__proto__) {
        return object;
    } else {
        // Try recursive cloning of an object/array
        try {
            Object.keys(object);
            for(var key in object) {
                clone[key] = clone_json(object[key]);
            }

            return clone;
        } catch(e) {
            // Other stuff isn't returned as a reference
            return object;
        }
    }
}

/**
 * Very basic unit test function
 */
function assertEquals(name, expected, test) {
    var result = test();
    if(result !== expected) {
        console.error('Unit test *' + name + '* failed :( ' + result + ' !== ' + expected);
    } else {
        // console.debug('Unit test ' + name + ' Passed ! :D');
    }
}

function array_remove_elements(array, remove) {
    return array.filter(function(element) {
        return remove.indexOf(element) == -1;
    });
}

function rand(min_rand, max_rand) {
    return min_rand + (Math.random()*1000000000 % (max_rand - min_rand));
}

/**
 * Micro-objects
 */
function Point(x, y) {
    this.x = x || 0;
    this.y = y || 0;
}

Point.prototype.setLocation = function(point) {
    this.x = point.x;
    this.y = point.y;
};

function Rectangle(x, y, w, h) {
    this.x = x || 0;
    this.y = y || 0;
    this.w = w || 0;
    this.h = h || 0;
}

Rectangle.prototype.setLocation = function(point) {
    this.x = point.x;
    this.y = point.y;
};

Rectangle.prototype.getLocation = function() {
    return new Point(this.x, this.y);
};

Rectangle.prototype.contains_point = function(point) {
    return point.x >= this.x &&
           point.x <= this.x + this.w &&
           point.y >= this.y &&
           point.y <= this.y + this.h;
};

Rectangle.prototype.intersects = function(rectangle) {
    var intersects = false;

    // Ignore 1px border to avoid false collisions
    var corners = [
        new Point(this.x + 1, this.y + 1),
        new Point(this.x + this.w - 1, this.y + 1),
        new Point(this.x + 1, this.y + this.h -1),
        new Point(this.x + this.w - 1, this.y + this.h - 1)
    ];

    for(var index in corners) {
        intersects = intersects || rectangle.contains_point(corners[index]);
    }

    // is rectangle in this
    corners = [
        new Point(rectangle.x + 1, rectangle.y + 1),
        new Point(rectangle.x + rectangle.w - 1, rectangle.y + 1),
        new Point(rectangle.x + 1, rectangle.y + rectangle.h - 1),
        new Point(rectangle.x + rectangle.w - 1, rectangle.y + rectangle.h - 1)
    ];

    for(var index in corners) {
        intersects = intersects || this.contains_point(corners[index]);
    }

    return intersects;
}
