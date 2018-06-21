'use strict';

define('demo/tests/app.lint-test', [], function () {
  'use strict';

  QUnit.module('ESLint | app');

  QUnit.test('app.js', function (assert) {
    assert.expect(1);
    assert.ok(true, 'app.js should pass ESLint\n\n');
  });

  QUnit.test('controllers/application.js', function (assert) {
    assert.expect(1);
    assert.ok(true, 'controllers/application.js should pass ESLint\n\n');
  });

  QUnit.test('controllers/book-ticket.js', function (assert) {
    assert.expect(1);
    assert.ok(false, 'controllers/book-ticket.js should pass ESLint\n\n37:2 - Mixed spaces and tabs. (no-mixed-spaces-and-tabs)\n45:2 - Mixed spaces and tabs. (no-mixed-spaces-and-tabs)\n77:2 - Mixed spaces and tabs. (no-mixed-spaces-and-tabs)\n114:2 - Mixed spaces and tabs. (no-mixed-spaces-and-tabs)\n136:26 - \'i\' is already defined. (no-redeclare)\n136:31 - \'k\' is already defined. (no-redeclare)\n138:26 - \'obj\' is already defined. (no-redeclare)');
  });

  QUnit.test('controllers/view-details.js', function (assert) {
    assert.expect(1);
    assert.ok(false, 'controllers/view-details.js should pass ESLint\n\n68:14 - \'res\' is not defined. (no-undef)\n69:14 - \'res\' is not defined. (no-undef)\n70:14 - \'res\' is not defined. (no-undef)');
  });

  QUnit.test('resolver.js', function (assert) {
    assert.expect(1);
    assert.ok(true, 'resolver.js should pass ESLint\n\n');
  });

  QUnit.test('router.js', function (assert) {
    assert.expect(1);
    assert.ok(true, 'router.js should pass ESLint\n\n');
  });

  QUnit.test('routes/book-ticket.js', function (assert) {
    assert.expect(1);
    assert.ok(true, 'routes/book-ticket.js should pass ESLint\n\n');
  });

  QUnit.test('routes/view-details.js', function (assert) {
    assert.expect(1);
    assert.ok(true, 'routes/view-details.js should pass ESLint\n\n');
  });
});
define('demo/tests/test-helper', ['demo/app', 'demo/config/environment', '@ember/test-helpers', 'ember-qunit'], function (_app, _environment, _testHelpers, _emberQunit) {
  'use strict';

  (0, _testHelpers.setApplication)(_app.default.create(_environment.default.APP));

  (0, _emberQunit.start)();
});
define('demo/tests/tests.lint-test', [], function () {
  'use strict';

  QUnit.module('ESLint | tests');

  QUnit.test('test-helper.js', function (assert) {
    assert.expect(1);
    assert.ok(true, 'test-helper.js should pass ESLint\n\n');
  });

  QUnit.test('unit/controllers/application-test.js', function (assert) {
    assert.expect(1);
    assert.ok(true, 'unit/controllers/application-test.js should pass ESLint\n\n');
  });

  QUnit.test('unit/controllers/book-ticket-test.js', function (assert) {
    assert.expect(1);
    assert.ok(true, 'unit/controllers/book-ticket-test.js should pass ESLint\n\n');
  });

  QUnit.test('unit/controllers/view-details-test.js', function (assert) {
    assert.expect(1);
    assert.ok(true, 'unit/controllers/view-details-test.js should pass ESLint\n\n');
  });

  QUnit.test('unit/routes/book-ticket-test.js', function (assert) {
    assert.expect(1);
    assert.ok(true, 'unit/routes/book-ticket-test.js should pass ESLint\n\n');
  });

  QUnit.test('unit/routes/view-details-test.js', function (assert) {
    assert.expect(1);
    assert.ok(true, 'unit/routes/view-details-test.js should pass ESLint\n\n');
  });
});
define('demo/tests/unit/controllers/application-test', ['qunit', 'ember-qunit'], function (_qunit, _emberQunit) {
  'use strict';

  (0, _qunit.module)('Unit | Controller | application', function (hooks) {
    (0, _emberQunit.setupTest)(hooks);

    // Replace this with your real tests.
    (0, _qunit.test)('it exists', function (assert) {
      let controller = this.owner.lookup('controller:application');
      assert.ok(controller);
    });
  });
});
define('demo/tests/unit/controllers/book-ticket-test', ['qunit', 'ember-qunit'], function (_qunit, _emberQunit) {
  'use strict';

  (0, _qunit.module)('Unit | Controller | book-ticket', function (hooks) {
    (0, _emberQunit.setupTest)(hooks);

    // Replace this with your real tests.
    (0, _qunit.test)('it exists', function (assert) {
      let controller = this.owner.lookup('controller:book-ticket');
      assert.ok(controller);
    });
  });
});
define('demo/tests/unit/controllers/view-details-test', ['qunit', 'ember-qunit'], function (_qunit, _emberQunit) {
  'use strict';

  (0, _qunit.module)('Unit | Controller | view-details', function (hooks) {
    (0, _emberQunit.setupTest)(hooks);

    // Replace this with your real tests.
    (0, _qunit.test)('it exists', function (assert) {
      let controller = this.owner.lookup('controller:view-details');
      assert.ok(controller);
    });
  });
});
define('demo/tests/unit/routes/book-ticket-test', ['qunit', 'ember-qunit'], function (_qunit, _emberQunit) {
  'use strict';

  (0, _qunit.module)('Unit | Route | book-ticket', function (hooks) {
    (0, _emberQunit.setupTest)(hooks);

    (0, _qunit.test)('it exists', function (assert) {
      let route = this.owner.lookup('route:book-ticket');
      assert.ok(route);
    });
  });
});
define('demo/tests/unit/routes/view-details-test', ['qunit', 'ember-qunit'], function (_qunit, _emberQunit) {
  'use strict';

  (0, _qunit.module)('Unit | Route | view-details', function (hooks) {
    (0, _emberQunit.setupTest)(hooks);

    (0, _qunit.test)('it exists', function (assert) {
      let route = this.owner.lookup('route:view-details');
      assert.ok(route);
    });
  });
});
define('demo/config/environment', [], function() {
  var prefix = 'demo';
try {
  var metaName = prefix + '/config/environment';
  var rawConfig = document.querySelector('meta[name="' + metaName + '"]').getAttribute('content');
  var config = JSON.parse(unescape(rawConfig));

  var exports = { 'default': config };

  Object.defineProperty(exports, '__esModule', { value: true });

  return exports;
}
catch(err) {
  throw new Error('Could not read config from meta tag with name "' + metaName + '".');
}

});

require('demo/tests/test-helper');
EmberENV.TESTS_FILE_LOADED = true;
//# sourceMappingURL=tests.map
