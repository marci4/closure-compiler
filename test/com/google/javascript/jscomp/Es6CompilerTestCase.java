/*
 * Copyright 2015 The Closure Compiler Authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.javascript.jscomp;

import com.google.javascript.jscomp.CompilerOptions.LanguageMode;

/**
 * CompilerTestCase for passes that run in both ES6 and ES5 modes.
 *
 * @author moz@google.com (Michael Zhou)
 */
public abstract class Es6CompilerTestCase extends CompilerTestCase {

  /**
   * Verifies that the compiler pass's JS output matches the expected output, under
   * both ES5 and ES6 modes.
   *
   * @param js Input
   * @param expected Expected JS output
   */
  @Override
  public void test(String js, String expected) {
    setAcceptedLanguage(LanguageMode.ECMASCRIPT6);
    super.test(js, expected);
    setAcceptedLanguage(LanguageMode.ECMASCRIPT5);
    super.test(js, expected);
  }

  /**
   * Verifies that the compiler pass's JS output matches the expected output, under
   * a specific language mode.
   *
   * @param js Input
   * @param expected Expected JS output
   */
  public void test(String js, String expected, LanguageMode mode) {
    setAcceptedLanguage(mode);
    super.test(js, expected);
  }

  /**
   * Verifies that the compiler pass's JS output matches the expected output, under
   * just ES6. Usually this implies that the input contains ES6 features.
   *
   * @param js Input
   * @param expected Expected JS output
   */
  public void testEs6(String js, String expected) {
    test(js, expected, LanguageMode.ECMASCRIPT6);
  }

  /**
   * Verifies that the compiler pass's JS output matches the expected output,
   * under both ES5 and ES6 modes.
   *
   * @param moduleInputs Module inputs
   * @param expected Expected JS outputs (one per module)
   */
  public void testModule(String[] moduleInputs, String[] expected) {
    setAcceptedLanguage(LanguageMode.ECMASCRIPT6);
    super.test(createModuleStar(moduleInputs), expected, null);
    setAcceptedLanguage(LanguageMode.ECMASCRIPT5);
    super.test(createModuleStar(moduleInputs), expected, null);
  }

  /**
   * Verifies that the compiler pass's JS output is the same as its input, under
   * both ES5 and ES6 modes.
   *
   * @param js Input and output
   */
  @Override
  public void testSame(String js) {
    setAcceptedLanguage(LanguageMode.ECMASCRIPT6);
    super.test(js, js);
    setAcceptedLanguage(LanguageMode.ECMASCRIPT5);
    super.test(js, js);
  }

  /**
   * Verifies that the compiler generates the given error for the given input,
   * under both ES5 and ES6 modes.
   *
   * @param js Input
   * @param error Expected error
   */
  @Override
  public void testError(String js, DiagnosticType error) {
    setAcceptedLanguage(LanguageMode.ECMASCRIPT6);
    super.testError(js, error);
    setAcceptedLanguage(LanguageMode.ECMASCRIPT5);
    super.testError(js, error);
  }

  /**
   * Verifies that the compiler generates the given error for the given input,
   * under a specific language mode.
   *
   * @param js Input
   * @param error Expected error
   * @param mode Specific language mode
   */
  public void testError(String js, DiagnosticType error, LanguageMode mode) {
    setAcceptedLanguage(mode);
    assertNotNull("Must assert an error", error);
    super.test(js, null, error, null);
  }

  /**
   * Verifies that the compiler generates the given error for the given input,
   * under just ES6. Usually this implies that the input contains ES6 features.
   *
   * @param js Input
   * @param error Expected error
   */
  public void testErrorEs6(String js, DiagnosticType error) {
    testError(js, error, LanguageMode.ECMASCRIPT6);
  }
}

